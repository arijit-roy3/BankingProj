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
@WebServlet("/adminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int adminId=Integer.parseInt(request.getParameter("adminId"));
		String apwd=request.getParameter("password");
		
		RequestDispatcher dispatcher=null;
		PreparedStatement st=null;
		Connection con=null;
		HttpSession session=request.getSession(); 
		
		if(apwd==null || apwd.equals("")) {
			request.setAttribute("status", "invalidUpwd");
			dispatcher=request.getRequestDispatcher("adminLogin.jsp");
			dispatcher.forward(request, response);
		}
		if(adminId<1) {
			request.setAttribute("status", "invalidAdminId");
			dispatcher=request.getRequestDispatcher("adminLogin.jsp");
			dispatcher.forward(request, response);
		}
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EazyBank", "root", "password");
			 st=con.prepareStatement("select * from admin where aid=? and apassword =?");
			st.setInt(1,adminId);
			st.setString(2,apwd);
			
			ResultSet rs=st.executeQuery();
			//System.out.println(rs.next());
			if(rs.next()) {
				session.setAttribute("admin",rs.getString("aname"));
				
				dispatcher=request.getRequestDispatcher("adminIndex.jsp");
			}else {
				request.setAttribute("status", "failed");
				dispatcher=request.getRequestDispatcher("adminLogin.jsp");
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
