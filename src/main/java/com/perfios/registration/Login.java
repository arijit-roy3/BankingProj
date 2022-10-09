package com.perfios.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int accountNo=Integer.parseInt(request.getParameter("accountNo"));
		String upwd=request.getParameter("password");
		
		RequestDispatcher dispatcher=null;
		PreparedStatement st=null;
		Connection con=null;
		HttpSession session=request.getSession(); 
		
		if(upwd==null || upwd.equals("")) {
			request.setAttribute("status", "invalidUpwd");
			dispatcher=request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		if(accountNo<1) {
			request.setAttribute("status", "invalidAccountNo");
			dispatcher=request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EazyBank", "root", "password");
			 st=con.prepareStatement("select * from users where id=? and upwd =?");
			st.setInt(1,accountNo);
			st.setString(2,upwd);
			
			ResultSet rs=st.executeQuery();
			//System.out.println(rs.next());
			if(rs.next()) {
				session.setAttribute("name",rs.getString("uname"));				
				session.setAttribute("id", rs.getInt("id"));
				session.setAttribute("balance", rs.getDouble("balance"));
				session.setAttribute("password", rs.getString("upwd"));
				dispatcher=request.getRequestDispatcher("index.jsp");
			}else {
				request.setAttribute("status", "failed");
				dispatcher=request.getRequestDispatcher("login.jsp");
			}
			
//			
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				st.close();
				con.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}			
			
			
		}
	}

}
