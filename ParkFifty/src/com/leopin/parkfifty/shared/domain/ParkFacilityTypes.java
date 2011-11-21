package com.leopin.parkfifty.shared.domain;

public enum ParkFacilityTypes {

	COVERED("C"), OPEN("O"), STREET("S"), MULTI_STOREY("MS");
	
	String parkFacilityType;
	
	ParkFacilityTypes(String type) {
		this.parkFacilityType = type;
	}
	
	public String getParkFacilityTypeCd() {
		return parkFacilityType;
	}
}
