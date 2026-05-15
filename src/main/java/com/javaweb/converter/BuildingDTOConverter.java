package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.config.ModelMapperConfig;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {
	@Autowired
	private ModelMapperConfig modelMapperConfig;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO buildingDTO = modelMapperConfig.modelMapper().map(item, BuildingDTO.class);
		buildingDTO.setAddress(item.getStreet() + ", " + item.getWard() + ", " + item.getDistrict().getName());
		List<RentAreaEntity> rentAreas = item.getRentAreas();
		String rentarea = rentAreas.stream().map(it -> String.valueOf(it.getValue())).collect(Collectors.joining(","));
		buildingDTO.setRent_area(rentarea);
		return buildingDTO;
	}
}
