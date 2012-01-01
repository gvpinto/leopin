package com.leopin.parkfifty.shared.exception;

public enum AppErrorKey {

	ADMIN_COMPANY_NOT_FOUND("error.app.admin.company.not.found"),
	ADMIN_COMPANY_EXISTS("error.app.admin.company.exists"),
	ADMIN_COMPANY_BINDING_ERRORS("error.app.admin.company.binding.errors"),
	ADMIN_LOCATION_EXISTS("error.app.admin.location.exists"),
	ADMIN_LOCATION_BINDING_ERRORS("error.app.admin.location.binding.errors"),
	ADMIN_USER_EXISTS("error.app.admin.user.exists"),
	ADMIN_COMPANYUSER_BINDING_ERRORS("error.app.admin.companyuser.binding.errors"),
	ADMIN_COMPANYUSER_OWNER_EXISTS("error.app.admin.companyuser.owner.exists"),
	ADMIN_COMPANYUSER_USER_NOT_FOUND("error.app.admin.companyuser.user.not.found");

	private String errorKey;
	
	AppErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}
	
	
	public String getErrorKey() {
		return errorKey;
	}
	
}
