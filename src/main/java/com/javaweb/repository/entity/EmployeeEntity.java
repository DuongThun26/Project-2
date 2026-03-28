package com.javaweb.repository.entity;

public class EmployeeEntity {
	private int id;
	private String employee_name, phone, email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}		