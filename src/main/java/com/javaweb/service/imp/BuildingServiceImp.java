package com.javaweb.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImp implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;

	@Override
	public List<BuildingDTO> getAllBuilding(Map<String, Object> params) {
		// TODO Auto-generated method stub
		List<BuildingDTO> result = new ArrayList<>();
		List<BuildingEntity> buildings = buildingRepository.getAllBuilding(params);
		for(BuildingEntity item : buildings) {
			BuildingDTO buildingDTO = new BuildingDTO();
			buildingDTO.setBuilding_name(item.getBuilding_name());
			buildingDTO.setId(item.getId());
			buildingDTO.setDirection(item.getDirection());
			buildingDTO.setFloor_area(item.getFloor_area());
			buildingDTO.setNumber_of_floors(item.getNumber_of_floors());
			buildingDTO.setNumber_of_basement_floors(item.getNumber_of_basement_floors());
			result.add(buildingDTO);
		}
		return result;
	}
	
	
}
