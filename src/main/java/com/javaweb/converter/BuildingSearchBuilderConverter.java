package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
																			.setName(MapUtil.getObject(params, "name", String.class))
																			.setFloorArea(MapUtil.getObject(params, "floorArea", Long.class))
																			.setStreet(MapUtil.getObject(params, "street", String.class))
																			.setWard(MapUtil.getObject(params, "ward", String.class))
																			.setRentAreaFrom(MapUtil.getObject(params, "rentAreaFrom", Long.class))
																			.setRentAreaTo(MapUtil.getObject(params, "rentAreaTo", Long.class))
																			.setNumberOfBaseement(MapUtil.getObject(params, "numberOfBasement", Integer.class))
																			.setManagerName(MapUtil.getObject(params, "managerName", String.class))
																			.setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Long.class))
																			.setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Long.class))
																			.setDistrictId(MapUtil.getObject(params, "districtId", Integer.class))	
																			.typeCode(typeCode)
																			.build();
		return buildingSearchBuilder;
	}
}
