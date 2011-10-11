package com.leopin.parkfifty.shared.exception;

public final class ErrorKeys {
	public static final String ERROR_DEFAULT = "error.default"; // Exception Occurred
	public static final String ERROR_UNEXPECTED = "error.unexpected"; // Unexpected Error Occurred

	public static final String ERROR_APP_ADMIN_COMPANY_NOT_FOUND = "error.app.admin.company.not.found";
	public static final String ERROR_APP_ADMIN_COMPANY_EXISTS = "error.app.admin.company.exists";
	public static final String ERROR_APP_ADMIN_COMPANY_BINDING_ERRORS = "error.app.admin.company.binding.errors";
	
	public static final String ERROR_SYS_ADMIN_ADD_COMPANY = "error.sys.admin.add.company"; // Error occurred when inserting company {0}
	public static final String ERROR_SYS_ADMIN_GET_COMPANY = "error.sys.admin.get.company";
	public static final String ERROR_SYS_ADMIN_DELETE_COMPANY = "error.sys.admin.delete.company";
}