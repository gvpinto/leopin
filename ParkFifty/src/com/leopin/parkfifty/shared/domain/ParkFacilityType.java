package com.leopin.parkfifty.shared.domain;

import java.io.Serializable;

public enum ParkFacilityType implements Serializable {

	COVERED("C"), OPEN("O"), STREET("S"), MULTI_STOREY("MS");
	
	String parkFacilityType;
	
	ParkFacilityType(String type) {
		this.parkFacilityType = type;
	}
	
	public String getParkFacilityTypeCd() {
		return parkFacilityType;
	}
}
