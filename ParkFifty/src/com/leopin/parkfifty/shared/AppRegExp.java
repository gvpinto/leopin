package com.leopin.parkfifty.shared;

public class AppRegExp {
//	public static final String EMAIL = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
	public static final String EMAIL = "^[a-z0-9_\\-]+(\\.[_a-z0-9\\-]+)*@([_a-z0-9\\-]+\\.)+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)$";
	public static final String URL = "(http|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
	public static final String COMPANY_NAME = "^[a-zA-Z0-9\\s\\-\\_]{3,100}$";
	public static final String PHONE_NUM = "(^[0-9]{10,15}$)";
	public static final String EMPTY_STRING = "()";
}
