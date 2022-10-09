package com.perfios.registration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.perfios.dao.TransactionsDao;
import com.perfios.dao.UsersDao;
import com.perfios.pojo.Transactions;
import com.perfios.pojo.Users;

/**
 * Servlet implementation class AdminTransactions
 */
@WebServlet("/adminTransactions")
public class AdminTransactions extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TransactionsDao transactionsDao;
    public void init() {
		transactionsDao=new TransactionsDao();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Transactions> listTransaction = transactionsDao.selectAllTransactions();
		request.setAttribute("listTransaction", listTransaction);
		HttpSession session = request.getSession();
		session.setAttribute("transactions", listTransaction);
		RequestDispatcher dispatcher = request.getRequestDispatcher("adminTransactions.jsp");
		dispatcher.forward(request, response);
	}

	

}
