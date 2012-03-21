package com.leopin.parkfifty.shared.constants;

public enum CompanyUserFields {

	UiUsername("uiUserUsername"),
	UiPassword("uiUserPassword"),
	UiTitle("uiUserTitle"),
	UiFirstName("uiUserFirstName"),
	UiMiddleInitial("uiUserMiddleInitial"),
	UiLastName("uiUserLastName"),
	UiSuffix("uiUserSuffix"),
	UiEmail("uiUserEmail"),
	UiPriPhone("uiUserPriPhone"),
	UiSecPhone("uiUserSecPhone"),
	UiFax("uiUserFax");
	

	String id;
	String labelKey;

	
	CompanyUserFields(String id) {
		this(id, "");
	}
	
	CompanyUserFields(String id, String labelKey) {
		this.id = id;
		this.labelKey = labelKey;
	}

	public String getId() {
		return id;
	}
	
	public String getLabelKey() {
		return labelKey;
	}
	
	public String toString() {
		return id;
	}
}
