package com.leopin.parkfifty.shared.exception;

public enum SysErrorKey {
	
	DEFAULT("error.app.default"), // Exception Occurred
	UNEXPECTED("error.app.unexpected"), // Unexpected Error Occurred
	ADMIN_ADD_COMPANY("error.sys.admin.add.company"), // Error occurred when inserting company {0}
	ADMIN_GET_COMPANY("error.sys.admin.get.company"),
	ADMIN_DELETE_COMPANY("error.sys.admin.delete.company"),
	ADMIN_ADD_LOCATION("error.sys.admin.add.location"),	
	ADMIN_ADD_USER("error.sys.admin.add.user");


	private String errorKey;
	
	SysErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}
	
	
	public String getErrorKey() {
		return errorKey;
	}
}
