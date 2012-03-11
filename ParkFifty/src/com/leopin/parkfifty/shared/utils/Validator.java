package com.leopin.parkfifty.shared.utils;

public final class Validator {
	
	public static boolean validator(String value, String regex, boolean isRequired) {
		return Utils.validate(value, AppRegExp.COMPANY_NAME, isRequired);
	}

	public static boolean validateName(String value) {
		return Utils.validate(value, AppRegExp.COMPANY_NAME, true);
	}

	public static boolean validateUrl(String value) {
		return Utils.validate(value, AppRegExp.URL, true);
	}

	public static boolean validateEmail(String value) {
		return Utils.validate(value, AppRegExp.EMAIL, true);
	}

	public static boolean validatePriPhone(String value) {
		return Utils.validate(value, AppRegExp.PHONE_NUM, true);
	}

	public static boolean validateOtherPhone(String value) {
		return Utils.validate(value, AppRegExp.PHONE_NUM, false);
	}
	
	public static boolean validateUsername(String value) {
		return Utils.validate(value, AppRegExp.USER_ID, true);
	}

	public static boolean validatePassword(String value) {
		return Utils.validate(value, AppRegExp.PASSWORD, true);
	}
	
	public static boolean validateTitle(String value) {
		return Utils.validate(value, AppRegExp.TITLE, false);
	}

	public static boolean validateUserName(String value) {
		return Utils.validate(value, AppRegExp.NAME, true);
	}


	public static boolean validateMiddleInitial(String value) {
		return Utils.validate(value, AppRegExp.MIDDLEINITIAL, false);
	}
	
	public static boolean validateSuffix(String value) {
		return Utils.validate(value, AppRegExp.SUFFIX, false);
	}
	
}
