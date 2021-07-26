package com.attendence.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.attendence.bean.Employee;
import com.attendence.dao.RegisterDao;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(Register.class);

	private RegisterDao rDao;

	public void init() {
		rDao = new RegisterDao();
	}

	public Register() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("This is a logging statement from log4j");
		String html = "<html><h2>Log4j has been initialized successfully!</h2></html>";
		response.getWriter().println(html);
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertEmployee(request, response);
				break;
			case "/delete":
				deleteEmployee(request, response);
				break;
			case "/edit":
				ShowEditForm(request, response);
				break;
			case "/update":
				updateEmployee(request, response);
				break;
			default:
				listEmp(request, response);
				break;
			}

		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listEmp(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ServletException {
		List<Employee> listEmp = rDao.selectAllEmp();
		request.setAttribute("listEmp", listEmp);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Emplist.html");
		dispatcher.forward(request, response);
	}

	public void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("EmpRegistration.html");
		dispatcher.forward(request, response);
	}

	private void ShowEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee existingEmp = rDao.selectEmp(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("EmpRegistration.html");
		request.setAttribute("employee", existingEmp);
		dispatcher.forward(request, response);
	}

	private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String ename = request.getParameter("ename");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");

		Employee newEmp = new Employee(ename, email, phone, password);
		rDao.insertEmployee(newEmp);
		response.sendRedirect("list");
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String ename = request.getParameter("ename");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		Employee updEmp = new Employee(id, ename, email, phone, password);
		rDao.updateEmployee(updEmp);
		response.sendRedirect("list");
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		rDao.deleteEmployee(id);
		response.sendRedirect("list");
	}
	
	
}