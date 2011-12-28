package com.leopin.parkfifty.shared.domain;

public enum DurationType {
	MINUTE("Minutes"), 
	HOUR("Hourly"), 
	DAY("Daily"),
	WEEK("Weekly"),
	BIWEEK("BiWeekly"),
	MONTH("Monthly"),
	QUARTER("Quarterly"),
	SEMI_ANNUAL("Semi-Annual"),
	ANNUAL("Annually");
	
	String durationDesc;
	
	DurationType(String durationDesc) {
		this.durationDesc = durationDesc;
	}
	
	public String durationType() {
		return durationDesc;
	}
}
