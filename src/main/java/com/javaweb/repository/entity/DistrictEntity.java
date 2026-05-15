package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "district")
public class DistrictEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;
	
	@OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
	private List<BuildingEntity> buildings = new ArrayList<>();
	
	
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
	public List<BuildingEntity> getBuildings() {
		return buildings;
	}
	public void setBuildings(List<BuildingEntity> buildings) {
		this.buildings = buildings;
	}
	
}
