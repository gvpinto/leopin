package com.leopin.parkfifty.shared;

public class AppRegExp {
	public static final String EMAIL = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
	public static final String URL = "^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";
	public static final String COMPANY_NAME = "^[a-zA-Z0-9\\s\\-\\_]{3,100}$";
	public static final String PHONE_NUM = "^[0-9]{9,15}$";
}
