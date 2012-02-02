package com.leopin.parkfifty.shared.utils;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;

public abstract class Utils {

	public static String scrubPhoneNum(String phoneNum) {
		return CharMatcher.JAVA_LETTER_OR_DIGIT.retainFrom(Strings.nullToEmpty(phoneNum));
	}
	
	public static String formatPhoneNum(String phoneNum) {
		
		phoneNum = stripChars(phoneNum);
		
		String formattedPhoneNum = ""; 
		
		if (phoneNum != null & phoneNum.trim().length() > 0) {
			char[] digits = phoneNum.toCharArray();
			
			int start = digits.length - 10;
			boolean done = false;
			
			for (int i = 0; i < digits.length; i++) {
				if ((i == start || i == start + 1 || i == start + 2) && !done) {
					// "("
					formattedPhoneNum = formattedPhoneNum + "(";
					done = true;
				} else if (i == start + 3 && i != 0) {
					// ") "
					formattedPhoneNum = formattedPhoneNum + ") ";
				} else if (i == start + 6 && i != 0) {
					// "-"
					formattedPhoneNum = formattedPhoneNum + "-";
				}
				
				formattedPhoneNum = formattedPhoneNum + digits[i];
			}
		}
		
		return formattedPhoneNum;
		
	}
	
	
	public static String stripChars(String value) {
		if (value != null && value.trim().length() > 0)
			return value.replaceAll("[^0-9]","");
		else 
			return "";
	}
}
