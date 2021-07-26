package com.attendence.dao;

import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
//	private String dburl = "jdbc:mysql://localhost:3306/attendence";
//	private String dbename = "root";
//	private String dbpassword = "1295";
//	private String dbdriver = "com.mysql.cj.jdbc.Driver";

	public boolean validate(String n, String p) {
		Connection con = DAO.con();
		boolean status = false;
		try {
			String sql = "select * from employee  ";
			Statement pStatement = con.createStatement();
			System.out.println(n + p);

			ResultSet rSet = pStatement.executeQuery(sql);
			while (rSet.next()) {
				if (rSet.getString(1).equalsIgnoreCase(n)) {
					if (rSet.getString(4).equalsIgnoreCase(p)) {
						status = true;
						break;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
}