package com.javaweb.repository.entity;

public class BuildingEntity {
	private int id, district_id, number_of_basement, floor_area, rent_price;
	private String name, street, ward, direction, level, service_fee, rent_time, manager_name, manager_phone;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}
	public int getNumber_of_basement() {
		return number_of_basement;
	}
	public void setNumber_of_basement(int number_of_basement) {
		this.number_of_basement = number_of_basement;
	}
	public int getFloor_area() {
		return floor_area;
	}
	public void setFloor_area(int floor_area) {
		this.floor_area = floor_area;
	}
	public int getRent_price() {
		return rent_price;
	}
	public void setRent_price(int rent_price) {
		this.rent_price = rent_price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getService_fee() {
		return service_fee;
	}
	public void setService_fee(String service_fee) {
		this.service_fee = service_fee;
	}
	public String getRent_time() {
		return rent_time;
	}
	public void setRent_time(String rent_time) {
		this.rent_time = rent_time;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String namager_name) {
		this.manager_name = namager_name;
	}
	public String getManager_phone() {
		return manager_phone;
	}
	public void setManager_phone(String manager_phone) {
		this.manager_phone = manager_phone;
	}
	
	
}
