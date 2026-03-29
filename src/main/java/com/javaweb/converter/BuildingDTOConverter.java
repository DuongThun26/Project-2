package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.config.ModelMapperConfig;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RentAreaRepository rentAreaRepository;
	@Autowired
	private ModelMapperConfig modelMapperConfig;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		DistrictEntity districtEntity = districtRepository.getDistrictById(item.getDistrict_id());
		BuildingDTO buildingDTO = modelMapperConfig.modelMapper().map(item, BuildingDTO.class);
		buildingDTO.setAddress(item.getStreet() + ", " + item.getWard() + ", " + districtEntity.getName());
		List<RentAreaEntity> rentAreaEntities = rentAreaRepository.getValueAreaById(item.getId());
		String rentarea = rentAreaEntities.stream().map(it -> String.valueOf(it.getValue())).collect(Collectors.joining(","));
		buildingDTO.setRent_area(rentarea);
		return buildingDTO;
	}
}
