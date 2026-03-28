package com.javaweb.repository.entity;

public class DistrictEntity {
	private int id;
	private String name, code;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setId(int id) {
		this.id = id;
	}
}
