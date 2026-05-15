package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.javaweb.config.DBConnection;
import com.javaweb.repository.BuildingTypeRepository;
import com.javaweb.repository.entity.BuildingTypeEntity;

public class BuildingTypeRepositoryImpl implements BuildingTypeRepository{
	
	@Autowired
	private DBConnection dbConnection;
	@Override
	public List<BuildingTypeEntity> getBuildingByCode(List<String> typeCode) {
		// TODO Auto-generated method stub
		List<BuildingTypeEntity> buildingTypeEntities = new ArrayList<>();
		try(Connection conn = dbConnection.getConnection()){
			String sql = " SELECT bt.name FROM buildingtype bt ";
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				BuildingTypeEntity buildingTypeEntity = new BuildingTypeEntity();
				buildingTypeEntity.setName(rs.getString("name"));
			}
		}catch(SQLException e) {
			e.getStackTrace();
		}
		return buildingTypeEntities;
	}

}
