package com.perfios.registration;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname=request.getParameter("name");
		//System.out.println(uname);
		double balance=Double.parseDouble(request.getParameter("balance"));
		String uemail=request.getParameter("email");
		String pan=request.getParameter("pan");
		String upwd=request.getParameter("pass");
		String repwd=request.getParameter("re_pass");
		String umobile=request.getParameter("contact");		
		String address=request.getParameter("address");
		
//		System.out.println(uname);
//		System.out.println(balance);
//		System.out.println(uemail);
//		System.out.println(pan);
//		System.out.println(upwd);
//		System.out.println(repwd);
//		System.out.println(umobile);
//		System.out.println(address);
		
		RequestDispatcher dispatcher=null;
		PreparedStatement st=null;
		Connection con=null;
		
		if(uname==null || uname.equals("")) {
			request.setAttribute("status", "invalidUname");
			dispatcher=request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		if(uemail==null || uemail.equals("")) {
			request.setAttribute("status", "invalidUemail");
			dispatcher=request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		if(upwd==null || upwd.equals("")) {
			request.setAttribute("status", "invalidUpwd");
			dispatcher=request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}else if(!upwd.equals(repwd)) {
			request.setAttribute("status", "upwdNotMatch");
			dispatcher=request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		if(umobile==null || umobile.equals("") || umobile.length()!=10) {
			request.setAttribute("status", "invalidUmobile");
			dispatcher=request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		if(pan==null || pan.equals("") || pan.length()!=10 ) {
			request.setAttribute("status", "invalidPan");
			dispatcher=request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		if(balance<5000) {
			request.setAttribute("status", "invalidBalance");
			dispatcher=request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		if(address==null || address.equals("")) {
			request.setAttribute("status", "invalidAddress");
			dispatcher=request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EazyBank", "root", "password");
			 st=con.prepareStatement("insert into users(uname,balance, uemail,upan,upwd,umobile,uaddress) values(?,?,?,?,?,?,?)");
			st.setString(1,uname);
			st.setDouble(2,balance);
			st.setString(3,uemail);
			st.setString(4,pan);
			st.setString(5,upwd);
			st.setString(6,umobile);
			st.setString(7,address);
			
			int row=st.executeUpdate();
			dispatcher=request.getRequestDispatcher("registration.jsp");
			if(row>0) {
				request.setAttribute("status", "success");
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
