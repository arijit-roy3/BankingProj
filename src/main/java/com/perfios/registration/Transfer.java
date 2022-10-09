package com.perfios.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.perfios.dao.TransactionsDao;
import com.perfios.pojo.Transactions;


@WebServlet("/transfer")
public class Transfer extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accountNo=Integer.parseInt(request.getParameter("accountNo"));
		double amount=Double.parseDouble(request.getParameter("amount"));
		String type=request.getParameter("type");
		System.out.println(accountNo);
		System.out.println(amount);
		
		RequestDispatcher dispatcher=null;
		PreparedStatement st=null;
		PreparedStatement st2=null;
		Connection con=null;
		HttpSession session=request.getSession(); 
		int id= (Integer) session.getAttribute("id");
		TransactionsDao transactionDao=new TransactionsDao();
		
		if(accountNo==id) {
			request.setAttribute("status", "sameAccount");
			dispatcher=request.getRequestDispatcher("transfer.jsp");
			dispatcher.forward(request, response);
		}
		if(accountNo<1) {
			request.setAttribute("status", "invalidAccountNo");
			dispatcher=request.getRequestDispatcher("transfer.jsp");
			dispatcher.forward(request, response);
		}
		if(amount<1) {
			request.setAttribute("status", "invalidAmount");
			dispatcher=request.getRequestDispatcher("transfer.jsp");
			dispatcher.forward(request, response);
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EazyBank", "root", "password");
			//fetching the second user
			 st=con.prepareStatement("select * from users where id=?");
			st.setInt(1,accountNo);			
			ResultSet rs=st.executeQuery();
			
			//fetching the first user
				
			System.out.println(id);
			st2=con.prepareStatement("select * from users where id=?");
			st2.setInt(1,id);
			ResultSet rs1=st2.executeQuery();
			double amount1=0;
			if(rs1.next()) {
				amount1=rs1.getDouble("balance");
			}
			else {
				request.setAttribute("status", "internalError");
				dispatcher=request.getRequestDispatcher("transfer.jsp");
			}
			
			System.out.println(amount1);
			if(rs.next()) {				
				double balance=rs.getDouble("balance");
				System.out.println(balance);
				if(amount>amount1) {
					request.setAttribute("status", "notEnoughBalance");
					dispatcher=request.getRequestDispatcher("transfer.jsp");
				}
				else {
					amount1=amount1-amount;//first user
					balance=balance+amount;//second user
					//do the transaction
					st2=con.prepareStatement("update users set balance=? where id=?");
					st2.setDouble(1,amount1);
					st2.setInt(2, id);
					int chk2 = st2.executeUpdate();
//					ResultSet rs2=st2.executeQuery();
					
					st=con.prepareStatement("update users set balance=? where id=?");
					st.setDouble(1,balance);
					st.setInt(2, accountNo);
					int chk = st.executeUpdate();
//					ResultSet rs3=st.executeQuery();
					if(chk>0 && chk2>0) {
						request.setAttribute("status", "successfulTransaction");
						Transactions transaction=new Transactions();
						transaction.setAmount(amount);
						long millis=System.currentTimeMillis();  
				        java.sql.Date date=new java.sql.Date(millis);  
						transaction.setDate(date);
						transaction.setType(type);
						transaction.setFrom(id);
						transaction.setTo(accountNo);
						
						transactionDao.insertTransaction(transaction);
						
					}
					else {
						request.setAttribute("status", "internalError");
					}
					dispatcher=request.getRequestDispatcher("transfer.jsp");
				}
			}else {
				request.setAttribute("status", "accountNotPresent");
				dispatcher=request.getRequestDispatcher("transfer.jsp");
			}
			
		
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				st.close();
				//st2.close();				
				con.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}			
			
			
		}
	}

}
