package com.javaweb.repository.impl;

import java.sql.Statement;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.config.DBConnection;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	private void queryTableSpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		// TODO Auto-generated method stub
		Long rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
		Long rentAreaTo = buildingSearchBuilder.getRentAreaTo();
		if(rentAreaFrom != null) {
			where.append(" AND r.value " + " >= " + rentAreaFrom);
		}
		if(rentAreaTo != null) {
			where.append(" AND r.value " + " <= " + rentAreaTo);
		}
		Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
		Long rentPriceTo = buildingSearchBuilder.getRentFriceTo();
		if(rentPriceFrom != null) {
			where.append(" AND b.rent_price " + " >= " + rentPriceFrom);
		}
		if(rentPriceTo != null) {
			where.append(" AND b.rent_price " + " <= " + rentPriceTo);
		}
		// Java 7
		List <String> typeCode = buildingSearchBuilder.getTypeCode();
		if(typeCode != null && typeCode.size() != 0) {
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
		
	}

	private void queryTableNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		// TODO Auto-generated method stub
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for(Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if(!fieldName.startsWith("rentArea") && !fieldName.startsWith("rentPrice") && !fieldName.equals("typeCode")) {
					Object obj = item.get(buildingSearchBuilder);
					if(obj != null) {
						String value = obj.toString();
						if(StringUtil.checkString(value)) {
							if(NumberUtil.checkNumber(value)) {
								where.append(" AND " + fieldName + " = " + value + " ");
							}
							else {
								where.append(" AND " + fieldName + " like '%" + value + "%' ");
							}
						}
						
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		// TODO Auto-generated method stub
		Integer district_id = buildingSearchBuilder.getDistrictId();
		if(district_id != null) {
			sql.append(" inner join district d on d.id = b.district_id ");
		}
		Long rentareaFrom = buildingSearchBuilder.getRentAreaFrom();
		Long rentareaTo = buildingSearchBuilder.getRentAreaTo();
		if(rentareaFrom != null || rentareaTo != null) {
			sql.append(" inner join rentarea r on r.building_id = b.id ");
		}
		List <String> typeCode = buildingSearchBuilder.getTypeCode();
		if(typeCode != null && typeCode.size() != 0) {
			sql.append(" inner join buildingrenttype brt on brt.building_id = b.id ");
			sql.append(" inner join buildingtype bt on bt.id = brt.renttype_id ");
		}
		
	}
	@Override
	public List<BuildingEntity> getAllBuilding(BuildingSearchBuilder buildingSearchBuilder) {
		// TODO Auto-generated method stub
		List<BuildingEntity> buildings = new ArrayList<>();
		try(Connection conn = DBConnection.getConnection()){
			StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.street, b.ward, b.number_of_basement, b.floor_area, b.rent_price, b.direction, b.level, b.service_fee, b.manager_name, b.manager_phone, b.district_id FROM building b ");
			joinTable(buildingSearchBuilder, sql);   
			StringBuilder where = new StringBuilder(" where 1 = 1 ");
			queryTableNormal(buildingSearchBuilder, where);
			queryTableSpecial(buildingSearchBuilder, where);
			sql.append(where);
			sql.append(" GROUP BY b.id ");
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
