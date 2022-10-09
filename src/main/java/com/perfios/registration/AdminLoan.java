package com.perfios.registration;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.perfios.dao.LoanDao;
import com.perfios.dao.UsersDao;
import com.perfios.pojo.Loan;
import com.perfios.pojo.Users;

/**
 * Servlet implementation class AdminLoan
 */
@WebServlet("/adminLoan")
public class AdminLoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoanDao loandao;   
	public void init() {
		loandao = new LoanDao();
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Loan> listLoan = loandao.selectAllLoans();
		request.setAttribute("listLoan", listLoan);
		RequestDispatcher dispatcher = request.getRequestDispatcher("adminLoan.jsp");
		dispatcher.forward(request, response);
	}

}
