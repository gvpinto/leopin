package com.leopin.parkfifty.shared.constants;

public enum CompanyFields {

	UiName("uiName"),
	UiUrl("uiUrl"),
	UiEmail("uiEmail"),
	UiPriPhone("uiPriPhone"),
	UiSecPhone("uiSecPhone"),
	UiFax("uiFax");
	

	String id;
	String labelKey;

	
	CompanyFields(String id) {
		this(id, "");
	}
	CompanyFields(String id, String labelKey) {
		this.id = id;
		this.labelKey = labelKey;
	}

	public String getId() {
		return id;
	}
	
	public String getLabelKey() {
		return labelKey;
	}
	
}
