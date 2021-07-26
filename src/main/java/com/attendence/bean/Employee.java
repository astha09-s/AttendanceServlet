package com.attendence.bean;

public class Employee {

	private String ename, email, phone, password;
	private int id;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String ename, String email, String phone, String password) {
		super();
		this.ename = ename;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Employee(int id , String ename, String email, String phone, String password) {
		super();
		this.id = id;
		this.ename = ename;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}