package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;

@RestController
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	
	@GetMapping(value="/api/building")
	public List<BuildingDTO> getAllBuilding(Map<String, Object> params){
		List<BuildingDTO> buildings = buildingService.getAllBuilding(params);
		return buildings;
	}
}
