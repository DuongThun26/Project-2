package com.javaweb.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.config.ModelMapperConfig;
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
	
	@Autowired
	private ModelMapperConfig modelMapperConfig;
	

	@Override
	public List<BuildingDTO> getAllBuilding(Map<String, Object> params, List<String> typeCode) {
		// TODO Auto-generated method stub
		List<BuildingDTO> result = new ArrayList<>();
		List<BuildingEntity> buildings = buildingRepository.getAllBuilding(params, typeCode);
		for(BuildingEntity item : buildings) {				
			DistrictEntity districtEntity = districtRepository.getDistrictById(item.getDistrict_id());
			BuildingDTO buildingDTO = modelMapperConfig.modelMapper().map(item, BuildingDTO.class);
			buildingDTO.setAddress(item.getStreet() + ", " + item.getWard() + ", " + districtEntity.getName());
			result.add(buildingDTO);
		}
		return result;
	}
	
	
}
