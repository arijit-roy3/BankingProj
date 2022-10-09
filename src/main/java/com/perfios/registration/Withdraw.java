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

/**
 * Servlet implementation class Withdraw
 */
@WebServlet("/withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;      
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		double amount=Double.parseDouble(request.getParameter("amount"));		
		System.out.println(amount);
		
		RequestDispatcher dispatcher=null;
		PreparedStatement st=null;		
		Connection con=null;
		HttpSession session=request.getSession(); 
		TransactionsDao transactionDao=new TransactionsDao();
		
		if(amount<1) {
			request.setAttribute("status", "invalidAmount");
			dispatcher=request.getRequestDispatcher("transfer.jsp");
			dispatcher.forward(request, response);
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EazyBank", "root", "password");
			//fetching the second user
			int id= (Integer) session.getAttribute("id");
			 st=con.prepareStatement("select * from users where id=?");
			st.setInt(1,id);			
			ResultSet rs=st.executeQuery();
			
			//fetching the first user
				
			System.out.println(id);			
			
			
			if(rs.next()) {				
				double balance=rs.getDouble("balance");
				System.out.println(balance);
				if(amount>balance) {
					request.setAttribute("status", "notEnoughBalance");
					dispatcher=request.getRequestDispatcher("withdraw.jsp");
				}
				else {
					
					balance=balance-amount;					
					st=con.prepareStatement("update users set balance=? where id=?");
					st.setDouble(1,balance);
					st.setInt(2, id);
					int chk = st.executeUpdate();				
					
				
					if(chk>0) {
						request.setAttribute("status", "successfulWithdrawl");
						Transactions transaction=new Transactions();
						transaction.setAmount(amount);
						long millis=System.currentTimeMillis();  
				        java.sql.Date date=new java.sql.Date(millis);  
						transaction.setDate(date);
						transaction.setType("Withdraw");
						transaction.setFrom(id);
						transaction.setTo(id);
						
						transactionDao.insertTransaction(transaction);
						
					}
					else {
						request.setAttribute("status", "internalError");
					}
					dispatcher=request.getRequestDispatcher("withdraw.jsp");
				}
			}else {
				request.setAttribute("status", "internalError");
				dispatcher=request.getRequestDispatcher("withdraw.jsp");
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
