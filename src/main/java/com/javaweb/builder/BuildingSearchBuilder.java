package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
	private String name;
	private Long floorArea;
	private String ward;
	private String street;
	private Integer districtId;
	private Integer numberOfBasement;
	private List <String> typeCode = new ArrayList<>();
	private String managerName;
	private Long rentPriceFrom;
	private Long rentFriceTo;
	private Long rentAreaFrom;
	private Long rentAreaTo;
	
	public BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.floorArea = builder.floorArea;
		this.ward = builder.ward;
		this.street = builder.street;
		this.districtId = builder.districtId;
		this.numberOfBasement = builder.numberOfBasement;
		this.typeCode = builder.typeCode;
		this.managerName = builder.managerName;
		this.rentPriceFrom = builder.rentPriceFrom;
		this.rentFriceTo = builder.rentFriceTo;
		this.rentAreaFrom = builder.rentAreaFrom;
		this.rentAreaTo = builder.rentAreaTo;
	}
	public String getName() {
		return name;
	}
	public Long getFloorArea() {
		return floorArea;
	}
	public String getWard() {
		return ward;
	}
	public String getStreet() {
		return street;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public List<String> getTypeCode() {
		return typeCode;
	}
	public String getManagerName() {
		return managerName;
	}
	public Long getRentPriceFrom() {
		return rentPriceFrom;
	}
	public Long getRentFriceTo() {
		return rentFriceTo;
	}
	public Long getRentAreaFrom() {
		return rentAreaFrom;
	}
	public Long getRentAreaTo() {
		return rentAreaTo;
	}
	public static class Builder {
		private String name;
		private Long floorArea;
		private String ward;
		private String street;
		private Integer districtId;
		private Integer numberOfBasement;
		private List <String> typeCode = new ArrayList<>(); 
		private String managerName;
		private Long rentPriceFrom;
		private Long rentFriceTo;
		private Long rentAreaFrom;
		private Long rentAreaTo;
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setFloorArea(Long floorArea) {
			this.floorArea = floorArea;
			return this;
		}
		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}
		public Builder setDistrictId(Integer districtId) {
			this.districtId = districtId;
			return this;
		}
		public Builder setNumberOfBaseement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}
		public Builder typeCode(List<String> typeCode) {
			this.typeCode = typeCode;
			return this;
		}
		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}
		public Builder setRentPriceFrom(Long rentPriceFrom) {
			this.rentPriceFrom = rentPriceFrom;
			return this;
		}
		public Builder setRentPriceTo(Long rentPriceTo) {
			this.rentFriceTo = rentPriceTo;
			return this;
		}
		public Builder setRentAreaFrom(Long RentAreaFrom) {
			this.rentAreaFrom = RentAreaFrom;
					return this;
		}
		public Builder setRentAreaTo(Long RentAreaTo) {
			this.rentAreaTo = RentAreaTo;
			return this;
		}
		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
	}
}
