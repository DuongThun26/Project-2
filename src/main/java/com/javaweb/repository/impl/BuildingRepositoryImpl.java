package com.javaweb.repository.impl;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DBConnection;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	@Override
	public List<BuildingEntity> getAllBuilding(@RequestParam Map<String, Object> params) {
		// TODO Auto-generated method stub
		List<BuildingEntity> buildings = new ArrayList<>();
		try(Connection conn = DBConnection.getConnection()){
			StringBuilder sql = new StringBuilder("select b.ten_toa_nha, b.dien_tich_san, g.gia_thue, b.so_tang, b.so_tang_ham, b.huong, b.hang, d.ten_duong, p.ten_phuong, q.ten_quan, nv.ten_nhan_vien, nv.dien_thoai, nv.email\r\n"
					+ "from toa_nha b ");
			joinTable(params, sql);
			StringBuilder where = new StringBuilder(" where 1 = 1 ");
			sql.append(where);
			Statement stm = (Statement) conn.createStatement();
			ResultSet rs = stm.executeQuery(sql.toString());
			while(rs.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setId(rs.getInt("id"));
				buildingEntity.setBuilding_name(rs.getString("ten_toa_nha"));
				buildingEntity.setFloor_area(rs.getInt("dien_tich_san"));
				buildingEntity.setNumber_of_floors(rs.getInt("so_tang"));
				buildingEntity.setDirection(rs.getString("huong"));
				buildingEntity.setRate(rs.getString("hang"));
				buildings.add(buildingEntity);
			}
		}catch(SQLException e) {
			System.out.print(e);
		}
		return buildings;
	}

	private void joinTable(Map<String, Object> params, StringBuilder sql) {
		// TODO Auto-generated method stub
		
		
	}
	
}
