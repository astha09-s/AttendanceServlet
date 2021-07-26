package com.attendence.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.attendence.dao.LoginDao;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(Login.class);

	public Login() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ename = request.getParameter("ename");
		String password = request.getParameter("password");
	
		LoginDao loginDao = new LoginDao();

		if (loginDao.validate(ename, password)) {
			LOGGER.info("This is a logging statement from log4j");
	         
	        String html = "<html><h2>Log4j has been initialized successfully!</h2></html>";
	        response.getWriter().println(html);
			response.sendRedirect("Dashboard.html");
		} else {
			// HttpSession session = request.getSession();
			response.sendRedirect("EmpLogin.html");
		}

	}

}