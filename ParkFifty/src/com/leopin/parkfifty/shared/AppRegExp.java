package com.leopin.parkfifty.shared;

public abstract class AppRegExp {
//	public static final String EMAIL = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
	public static final String EMAIL = "^[a-z0-9_\\-]+(\\.[_a-z0-9\\-]+)*@([_a-z0-9\\-]+\\.)+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)$";
	public static final String URL = "(http|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
	public static final String COMPANY_CODE = "^[A-Z][\\_\\-0-9A-Z]{4,10}$";
	public static final String COMPANY_NAME = "^[\\w\\s\\-\\.\\,\\;\\']{3,100}$";
	public static final String PHONE_NUM = "(^[0-9]{10,15}$)";
	public static final String EMPTY_STRING = "()";
	
	public static final String LOCATION_NAME = "^[\\w\\s\\-\\'\\.\\,]{3,50}$";
	public static final String LOCATION_DESC = "^[\\w\\-\\,\\.\\s]{5,1024}$";
	
	public static final String STREET = "^[\\w\\s\\-\\,\\'\\.]{4,64}$";
	public static final String CITY = "^[a-zA-Z\\s\\-]{3,64}$";
	public static final String STATE_CD = "^[A-Z]{2}$";
	public static final String ZIP_CD = "^\\d{5}(\\-\\d{4})?$";
	public static final String COUNTRY_CD = "^[A-Z]{2,3}$";
	public static final String GEO_CD = "^\\-?[\\d]{2,3}\\.[\\d]{6,7}";
	
	public static final String SIMPLE_DESC = "^[\\w\\s]{1,128}";
	public static final String PARK_FACILITY_TYPE = "^(C|O|CM|S)$";
	
	public static final String USER_ID = "^[\\w]{6,12}";
//	public static final String PASSWORD = "^(?=.*[!@#$%^&*0-9\\-\\_]+[a-zA-Z]){6,20}";
//	public static final String PASSWORD = "^(?=.*[\\d$\\.!@#$%^&*-_])[\\w]{6,20}$";
	public static final String PASSWORD = "^(?=.*[0-9!@#$%^&*_+-\\.\\?:;,])[\\w0-9!@#$%^&*_+-\\.\\?:;,]{6,12}";
	public static final String ROLE = "^(O|S|A|U)$";
	public static final String TITLE = "^[a-zA-Z\\.]{2,4}";
	public static final String NAME = "^[a-zA-Z''-'\\s]{1,40}$";
	public static final String MIDDLEINITIAL = "^[A-Z]?";
	public static final String SUFFIX = "^[a-zA-Z]{1,3}";
	

	
}
