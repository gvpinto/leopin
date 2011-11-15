package com.leopin.parkfifty.shared;

public abstract class AppRegExp {
//	public static final String EMAIL = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
	public static final String EMAIL = "^[a-z0-9_\\-]+(\\.[_a-z0-9\\-]+)*@([_a-z0-9\\-]+\\.)+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)$";
	public static final String URL = "(http|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
	public static final String COMPANY_CODE = "^[A-Z][\\_\\-0-9A-Z]{4,9}$";
	public static final String COMPANY_NAME = "^[a-zA-Z0-9\\s\\-\\_\\.\\,\\;\\']{3,100}$";
	public static final String PHONE_NUM = "(^[0-9]{10,15}$)";
	public static final String EMPTY_STRING = "()";
	
	public static final String LOCATION_NAME = "^[a-zA-Z0-9\\s\\-\\_]{3,30}$";
	public static final String LOCATION_DESC = "^[a-zA-Z0-9\\-\\_\\,\\.\\s]{5,1024}$";
	
	public static final String STREET = "^[a-zA-Z0-9\\s\\-\\,\\'\\.]{4,64}$";
	public static final String CITY = "^[a-zA-Z\\s\\-]{3,64}$";
	public static final String STATE_CD = "^[A-Z]{2}$";
	public static final String ZIP_CD = "^\\d{5}(\\-\\d{4})?$";
	public static final String COUNTRY_CD = "^[A-Z]{2,3}$";
	public static final String GEO_CD = "^\\-?[\\d]{2,3}\\.[\\d]{6,7}";
	
	public static final String SIMPLE_DESC = "^[\\w\\s]{1,128}";
	public static final String PARK_FACILITY_TYPE = "^(C|O|CM|S)$";
	

	
}
