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
 * Servlet implementation class Account
 */
@WebServlet("/account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uemail=request.getParameter("email");
		String oldpass=request.getParameter("old_pass");
		String pan=request.getParameter("pan");
		String upwd=request.getParameter("pass");
		String repwd=request.getParameter("re_pass");
		String umobile=request.getParameter("contact");		
		String address=request.getParameter("address");
		
		System.out.println(uemail);
		System.out.println(oldpass);
		System.out.println(pan);
		System.out.println(upwd);
		System.out.println(repwd);
		System.out.println(umobile);
		System.out.println(address);
		
		RequestDispatcher dispatcher=null;
		PreparedStatement st=null;
		Connection con=null;
		HttpSession session=request.getSession(); 
		if(!oldpass.equals(session.getAttribute("password"))) {
			request.setAttribute("status", "passwordNotMatch");
			dispatcher=request.getRequestDispatcher("account.jsp");
			dispatcher.forward(request, response);
		}
		if(!upwd.equals(repwd)) {
			request.setAttribute("status", "upwdNotMatch");
			dispatcher=request.getRequestDispatcher("account.jsp");
			dispatcher.forward(request, response);
		}
		if(upwd !=null && repwd==null) {
			request.setAttribute("status", "retypePassword");
			dispatcher=request.getRequestDispatcher("account.jsp");
			dispatcher.forward(request, response);
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EazyBank", "root", "password");
			int id=(Integer)session.getAttribute("id");
			int row1=0,row2=0,row3=0,row4=0;
			
			if(!uemail.equals("")) {
				st=con.prepareStatement("update users set uemail=? where id=?");
				st.setString(1,uemail);
				st.setInt(2, id);				
				 row1=st.executeUpdate();
			}
			if(!upwd.equals("")) {
				st=con.prepareStatement("update users set upwd=? where id=?");
				st.setString(1,upwd);
				st.setInt(2, id);				
				 row2=st.executeUpdate();
				session.setAttribute("password", upwd);
			}
			if(!umobile.equals("")) {
				st=con.prepareStatement("update users set umobile=? where id=?");
				st.setString(1,umobile);
				st.setInt(2, id);				
				 row3=st.executeUpdate();
			}
			if(!address.equals("")) {
				st=con.prepareStatement("update users set uaddress=? where id=?");
				st.setString(1,address);
				st.setInt(2, id);				
				 row4=st.executeUpdate();
			}
			
			dispatcher=request.getRequestDispatcher("account.jsp");
			if(row1>0 || row2>0 || row3>0 || row4>0) {
				request.setAttribute("status", "success");
				response.sendRedirect("account.jsp");
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
