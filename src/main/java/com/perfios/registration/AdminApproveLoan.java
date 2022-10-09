package com.perfios.registration;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.perfios.dao.LoanDao;

/**
 * Servlet implementation class AdminApproveLoan
 */
@WebServlet("/adminApproveLoan")
public class AdminApproveLoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private LoanDao loandao;
    public void init() {
    	loandao=new LoanDao();
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			loandao.changeStatus(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/BankingProj/adminLoan");
	}

}
