package com.javaweb.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImp implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public List<BuildingDTO> getAllBuilding(Map<String, Object> params, List<String> typeCode) {
		// TODO Auto-generated method stub
		List<BuildingDTO> result = new ArrayList<>();
		List<BuildingEntity> buildings = buildingRepository.getAllBuilding(params, typeCode);
		for(BuildingEntity item : buildings) {
			BuildingDTO buildingDTO = new BuildingDTO();
			buildingDTO.setId(item.getId());
			buildingDTO.setName(item.getName());
			buildingDTO.setNumber_of_basement(item.getNumber_of_basement());
			buildingDTO.setFloor_area(item.getFloor_area());
			buildingDTO.setLevel(item.getLevel());
			buildingDTO.setManager_name(item.getManager_name());
			buildingDTO.setManager_phone(item.getManager_phone());
			buildingDTO.setRent_price(item.getRent_price());
			buildingDTO.setService_fee(item.getService_fee());
			DistrictEntity districtEntity = districtRepository.getDistrictById(item.getDistrict_id());
			buildingDTO.setAddress(item.getStreet() + ", " + item.getWard() + ", " + districtEntity.getName());
			buildingDTO.setDirection(item.getDirection());
			buildingDTO.setDistrict_id(item.getDistrict_id());
			result.add(buildingDTO);
		}
		return result;
	}
	
	
}
