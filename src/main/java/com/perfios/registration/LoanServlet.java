package com.perfios.registration;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.perfios.dao.LoanDao;
import com.perfios.pojo.Loan;

@WebServlet("/loan")
public class LoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		//System.out.println(uname);
		int cibilScore=Integer.parseInt(request.getParameter("cibilScore"));
		int age=Integer.parseInt(request.getParameter("age"));
		double salary=Double.parseDouble(request.getParameter("salary"));
		double amount=Double.parseDouble(request.getParameter("amount"));
		String designation=request.getParameter("designation");
		String company=request.getParameter("company");
		int tenure=Integer.parseInt(request.getParameter("tenure"));
		
		RequestDispatcher dispatcher=null;
		LoanDao loandao=new LoanDao();
		Loan loan=new Loan();
		
		if(tenure<6) {
			request.setAttribute("status", "invalidTenure");
			dispatcher=request.getRequestDispatcher("loan.jsp");
			dispatcher.forward(request, response);
			
		}
		
		else {
			loan.setName(name);
			loan.setCibilScore(cibilScore);
			loan.setAge(age);
			loan.setSalary(salary);
			loan.setAmount(amount);
			loan.setDesignation(designation);
			loan.setCompany(company);
			loan.setTenure(tenure);
			loan.setStatus("pending");
			
			try {
				int row=loandao.insertLoan(loan);
				dispatcher=request.getRequestDispatcher("loan.jsp");
				if(row>0) {
					request.setAttribute("status", "success");
					response.sendRedirect("index.jsp");
				}else {
					request.setAttribute("status", "failed");
					dispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
