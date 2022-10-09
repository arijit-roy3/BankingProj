package com.perfios.registration;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.perfios.dao.UsersDao;

/**
 * Servlet implementation class CheckBalance
 */
@WebServlet("/checkBalance")
public class CheckBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UsersDao user;
    public void init() {
    	user=new UsersDao();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(); 
		int id= (Integer) session.getAttribute("id");
		double balance=0;
		try {
			balance = user.getBalance(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("currentBalance", balance);
		RequestDispatcher dispatcher = request.getRequestDispatcher("checkBalance.jsp");
		dispatcher.forward(request, response);
	}

}
