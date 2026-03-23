package com.javaweb.model;

public class BuildingDTO {
	private int id;
	private String building_name;
	private int floor_area, number_of_floors, number_of_basement_floors;
	private String direction, rate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBuilding_name() {
		return building_name;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	public int getFloor_area() {
		return floor_area;
	}
	public void setFloor_area(int floor_area) {
		this.floor_area = floor_area;
	}
	public int getNumber_of_floors() {
		return number_of_floors;
	}
	public void setNumber_of_floors(int number_of_floors) {
		this.number_of_floors = number_of_floors;
	}
	public int getNumber_of_basement_floors() {
		return number_of_basement_floors;
	}
	public void setNumber_of_basement_floors(int number_of_basement_floors) {
		this.number_of_basement_floors = number_of_basement_floors;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}

	
}

