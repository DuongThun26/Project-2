package com.javaweb.repository.impl;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DBConnection;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	private void queryTableSpecial(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
		// TODO Auto-generated method stub
		String rentareaFrom = params.get("rentareaFrom") != null ? params.get("rentareaFrom").toString(): null;
		String rentareaTo = params.get("rentareaTo") != null ? params.get("rentareaTo").toString() : null;
		if(StringUtil.checkString(rentareaFrom)) {
			where.append(" AND r.value " + " >= " + rentareaFrom);
		}
		if(StringUtil.checkString(rentareaTo)) {
			where.append(" AND r.value " + " <= " + rentareaTo);
		}
		String rentpriceFrom = params.get("rentpriceFrom") != null ? params.get("rentpriceFrom").toString() : null;
		String rentpriceTo = params.get("rentpriceTo") != null ? params.get("rentpriceTo").toString(): null;
		if(StringUtil.checkString(rentpriceFrom)) {
			where.append(" AND b.rent_price " + " >= " + rentpriceFrom);
		}
		if(StringUtil.checkString(rentpriceTo)) {
			where.append(" AND b.rent_price " + " <= " + rentpriceTo);
		}
		// Java 7
		StringBuilder type = new StringBuilder();
		typeCode.forEach(i -> {
			if(type.length() > 0) {
				type.append(",");
			}
			type.append("'").append(i).append("'");
		});
		if(typeCode != null && typeCode.size() != 0) {
			where.append(" AND bt.code IN (" + type + ") ");
		}
		
	}

	private void queryTableNormal(Map<String, Object> params, StringBuilder where) {
		// TODO Auto-generated method stub
		for(Map.Entry<String, Object> item : params.entrySet()) {
			if(item.getKey().equals("district_id") || item.getKey().equals("number_of_basement")) {
				String value = item.getValue().toString();
				if(StringUtil.checkString(value)) {
					where.append(" AND " + item.getKey() + " = " + value + " ");
				}
			}
			else if(!item.getKey().startsWith("rentarea") && !item.getKey().startsWith("rentprice") && !item.getKey().equals("typeCode")) {
				String value = item.getValue().toString();
				if(StringUtil.checkString(value)) {
					where.append(" AND " + item.getKey() + " like '%" + value + "%' ");
				}
			}
		}
		
	}

	private void joinTable(Map<String, Object> params, List<String> typeCode, StringBuilder sql) {
		// TODO Auto-generated method stub
		String district_id = params.get("district_id") != null ? params.get("district_id").toString() : null;
		if(StringUtil.checkString(district_id)) {
			sql.append(" inner join district d on d.id = b.district_id ");
		}
		String rentareaFrom = params.get("rentareaFrom") != null ? params.get("rentareaFrom").toString() : null;
		String rentareaTo = params.get("rentareaTo") != null ? params.get("rentareaTo").toString() :null;
		if(StringUtil.checkString(rentareaFrom) || StringUtil.checkString(rentareaTo)) {
			sql.append(" inner join rentarea r on r.building_id = b.id ");
		}
		if(typeCode != null && typeCode.size() != 0) {
			sql.append(" inner join buildingrenttype brt on brt.building_id = b.id ");
			sql.append(" inner join buildingtype bt on bt.id = brt.renttype_id ");
		}
	}
	@Override
	public List<BuildingEntity> getAllBuilding(Map<String, Object> params, List <String> typeCode) {
		// TODO Auto-generated method stub
		List<BuildingEntity> buildings = new ArrayList<>();
		try(Connection conn = DBConnection.getConnection()){
			StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.street, b.ward, b.number_of_basement, b.floor_area, b.rent_price, b.direction, b.level, b.service_fee, b.manager_name, b.manager_phone, b.district_id FROM building b ");
			joinTable(params, typeCode, sql);   
			StringBuilder where = new StringBuilder(" where 1 = 1 ");
			queryTableNormal(params, where);
			queryTableSpecial(params, typeCode, where);
			sql.append(where);
			System.out.print(sql);
			Statement stm = (Statement) conn.createStatement();
			ResultSet rs = stm.executeQuery(sql.toString());
			while(rs.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setId(rs.getInt("id"));
				buildingEntity.setName(rs.getString("name"));
				buildingEntity.setFloor_area(rs.getInt("floor_area"));
				buildingEntity.setStreet(rs.getString("street"));
				buildingEntity.setWard(rs.getString("ward"));
				buildingEntity.setNumber_of_basement(rs.getInt("number_of_basement"));
				buildingEntity.setRent_price(rs.getInt("rent_price"));
				buildingEntity.setDirection(rs.getString("direction"));
				buildingEntity.setLevel(rs.getString("level"));
				buildingEntity.setService_fee(rs.getString("service_fee"));
				buildingEntity.setManager_name(rs.getString("manager_name"));
				buildingEntity.setManager_phone(rs.getString("manager_phone"));
				buildingEntity.setDistrict_id(rs.getInt("district_id"));
				buildings.add(buildingEntity);
			}
		}catch(SQLException e) {
			System.out.print(e);
		}
		return buildings;
	}

	
	
}
