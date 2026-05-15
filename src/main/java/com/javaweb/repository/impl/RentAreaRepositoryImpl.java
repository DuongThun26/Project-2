package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaweb.config.DBConnection;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository{
	
	
	@Autowired
	private DBConnection dbConnection;
	@Override
	public List<RentAreaEntity> getValueAreaById(int id) {
		// TODO Auto-generated method stub
		List<RentAreaEntity> rentArea = new ArrayList<>();
		String sql = " SELECT * FROM rentarea ra WHERE ra.building_id = " + id + " ";
		try(Connection conn = dbConnection.getConnection()){
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				rentAreaEntity.setId(rs.getInt("id"));
				rentAreaEntity.setValue(rs.getInt("value"));
//				rentAreaEntity.setBuildingId(rs.getInt("building_id"));
				rentArea.add(rentAreaEntity);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rentArea;
	}


}
