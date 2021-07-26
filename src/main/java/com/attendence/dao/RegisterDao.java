package com.attendence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.attendence.bean.Employee;

public class RegisterDao {

	private static final String INSERT_EMP_SQL = "INSERT INTO employee " + "(ename,email,phone,password)" + "(?,?,?)";
	private static final String SELECT_EMP_ID = "select id ename,email,phone,password";
	private static final String SELECT_ALL_EMP = "select * from employee";
	private static final String DELETE_EMP_SQL = "delete from employee where id=?";
	private static final String UPDATE_EMP_SQL = "update employee set ename =? email =? ,phone=? , password =? where id=?";

	public void insertEmployee(Employee employee) throws SQLException {
		Connection con = DAO.con();
		try {
			PreparedStatement pStatement = con.prepareStatement(INSERT_EMP_SQL);
			pStatement.setString(1, employee.getEname());
			pStatement.setString(2, employee.getEmail());
			pStatement.setString(3, employee.getPhone());
			pStatement.setString(4, employee.getPassword());
			pStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

	public Employee selectEmp(int id) {
		Connection con = DAO.con();
		Employee employee = null;
		try (PreparedStatement pStatement = con.prepareStatement(SELECT_EMP_ID)) {
			pStatement.setInt(1, id);
			System.out.println(pStatement);
			ResultSet rSet = pStatement.executeQuery();
			while (rSet.next()) {
				String ename = rSet.getString("ename");
				String email = rSet.getString("email");
				String phone = rSet.getString("phone");
				String password = rSet.getString("password");
				employee = new Employee(id, ename, email, phone, password);
			}
		} catch (SQLException ex) {
			printSQLException(ex);
		}
		return employee;
	}

	public List<Employee> selectAllEmp() {
		Connection con = DAO.con();
		List<Employee> employee = new ArrayList<>();
		// String sql="select * from employee";
		try (PreparedStatement pStatement = con.prepareStatement(SELECT_ALL_EMP)) {
			System.out.println(pStatement);
			ResultSet rSet = pStatement.executeQuery();
			while (rSet.next()) {
				int id = rSet.getInt("id");
				String ename = rSet.getString("ename");
				String email = rSet.getString("email");
				String phone = rSet.getString("phone");
				String password = rSet.getString("password");
				employee.add(new Employee(id, ename, email, phone, password));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	public boolean updateEmployee(Employee employee) throws SQLException {
		Connection con = DAO.con();
		boolean rowUpdated;
		try (PreparedStatement pStatement = con.prepareStatement(DELETE_EMP_SQL)) {
			System.out.println("update Employee:" + pStatement);
			pStatement.setString(1, employee.getEname());
			pStatement.setString(2, employee.getEmail());
			pStatement.setString(3, employee.getPhone());
			pStatement.setString(4, employee.getPassword());
			pStatement.setInt(5, employee.getId());

			rowUpdated = pStatement.executeUpdate() > 0;
		}

		return rowUpdated;
	}

	public boolean deleteEmployee(int id) throws SQLException {
		Connection con = DAO.con();
		boolean rowDeleted;
		try (PreparedStatement pStatement = con.prepareStatement(UPDATE_EMP_SQL)) {
			pStatement.setInt(1, id);
			rowDeleted = pStatement.executeUpdate() > 0;

		}
		return rowDeleted;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState:" + ((SQLException) ex).getSQLState());
				System.err.println("Error Code: " + ((SQLException) ex).getErrorCode());
				System.err.println("Message : " + ex.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("cause:" + t);
					t = t.getCause();
				}
			}
		}
	}
}