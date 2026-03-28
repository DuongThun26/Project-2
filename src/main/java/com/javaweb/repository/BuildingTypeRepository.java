package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.BuildingTypeEntity;

public interface BuildingTypeRepository {
	List<BuildingTypeEntity> getBuildingByCode(List <String> typeCode);
}
