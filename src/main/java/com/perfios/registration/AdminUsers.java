package com.perfios.registration;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.perfios.dao.UsersDao;
import com.perfios.pojo.Users;


@WebServlet("/adminUsers")
public class AdminUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersDao UsersDao;   
	public void init() {
		UsersDao = new UsersDao();
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Users> listUser = UsersDao.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("adminUsers.jsp");
		dispatcher.forward(request, response);
	}

}
