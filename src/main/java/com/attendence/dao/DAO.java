package com.attendence.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	
	
	public static Connection con()
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendence", "root", "1295");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
		
	}
	
	

}
