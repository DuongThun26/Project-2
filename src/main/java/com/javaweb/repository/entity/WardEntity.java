package com.javaweb.repository.entity;

public class WardEntity {
	private int id, district_id;
	private String ward_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWard_name() {
		return ward_name;
	}
	public void setWard_name(String ward_name) {
		this.ward_name = ward_name;
	}
	public int getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}

	
}
