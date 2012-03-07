package com.leopin.parkfifty.shared.utils;



/**
 * Utility class with methods for perform validation, checks formatting and other
 * utility functions required by the application
 * @author Glenn Pinto
 *
 */
public abstract class Utils {

	/**
	 * Script the given phone number of all special characters or spaces
	 * @param phoneNum Phone number to be cleaned up
	 * @return Cleaned phone number value
	 */
	public static String scrubPhoneNum(String phoneNum) {
		return stripChars(phoneNum);
//		return CharMatcher.JAVA_LETTER_OR_DIGIT.retainFrom(Strings.nullToEmpty(phoneNum));
	}
	
	/**
	 * Format a give phone number to (xxx) xxx-xxxx
	 * @param phoneNum Phone number to be formatted
	 * @return The formatted phone number
	 */
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

	/**
	 * Validate a value given a regular expression and is required flag
	 * @param value Value for validation
	 * @param regex Regular expression against which the value has to be validated
	 * @param isRequired If the value is required one then set it to true else set it to false
	 * @return true if it passes the validation, false if it fails the validation
	 */
	public static boolean validate(String value, String regex, boolean isRequired) {
		
		boolean result = false;
		
		if (!isEmptyOrNull(value)) {
			result = value.matches(regex);
		} else {
			if (isRequired) {
				result = false;
			} else {
				result = true;
			}
		}
		
		return result;
		
	}
	
	/**
	 * Check if the given value is Empty or Null
	 * @param value Value to check if its Empty or Null
	 * @return true if Empty or Null otherwise false
	 */
	public static boolean isEmptyOrNull(String value) {
		return (value == null || value.trim().length() == 0);
	}
}
