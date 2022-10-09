package com.perfios.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session=request.getSession();
		RequestDispatcher dispatcher=null;
		PreparedStatement st=null;
		Connection con=null;
		int id=(Integer)session.getAttribute("id");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EazyBank", "root", "password");
			 st=con.prepareStatement("delete from users where id=?");
			st.setInt(1,id);
			
			
			int row=st.executeUpdate();
			dispatcher=request.getRequestDispatcher("login.jsp");
			if(row>0) {
				request.setAttribute("status", "success");		
				
				session.invalidate();
				response.sendRedirect("login.jsp");
				
			}else {
				request.setAttribute("status", "failed");
				dispatcher.forward(request, response);
			}
			
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
