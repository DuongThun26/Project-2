package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.config.DBConnection;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;

@Repository
public class DistrictRepositoryImp implements DistrictRepository{

	@Override
	public DistrictEntity getDistrictById(int id) {
		// TODO Auto-generated method stub
		DistrictEntity districtEntity = new DistrictEntity();
		try(Connection conn = DBConnection.getConnection()){
			Statement stm = (Statement) conn.createStatement();
			String sql = " SELECT d.name FROM district d WHERE d.id = " + id + " ";
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				districtEntity.setName(rs.getString("name"));
			}
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
		return districtEntity;
	}

	
}
