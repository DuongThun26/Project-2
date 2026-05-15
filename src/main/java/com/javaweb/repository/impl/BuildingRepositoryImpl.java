package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepository{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<BuildingEntity> getAllBuilding(BuildingSearchBuilder buildingSearchBuilder) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("SELECT * FROM building b ");
		joinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		queryNomal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
		sql.append(where);
		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();
	}

	private void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
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
		// Java 8
		List <String> typeCode = buildingSearchBuilder.getTypeCode();
		if(typeCode != null && typeCode.size() != 0) {
			StringBuilder type = new StringBuilder();
			typeCode.forEach(i -> {
				if(type.length() > 0) {
					type.append(",");
				}
				type.append("'").append(i).append("'");
			});
			where.append(" AND bt.code IN (" + type + ") ");
		}
	}

	private void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		// TODO Auto-generated method stub
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for(Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if(fieldName.startsWith("rentArea") || fieldName.startsWith("rentPrice") || fieldName.equals("typeCode")) {
					continue;
				}
				else {
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
		Integer districtId = buildingSearchBuilder.getDistrictId();
		if(districtId != null) {
			sql.append(" INNER JOIN district d on d.id = b.district_id ");
		}
		Long rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
		Long renttAreaTo = buildingSearchBuilder.getRentAreaTo();
		if(rentAreaFrom != null || renttAreaTo != null) {
			sql.append(" INNER JOIN rentarea r on r.building_id = b.id ");
		}
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if(typeCode != null && typeCode.size() > 0) {
			sql.append(" INNER JOIN buildingrenttype brt on brt.building_id = b.id ");
			sql.append(" INNER JOIN buildingtype bt on bt.id = brt.renttype_id ");
		}
	}
	
}
