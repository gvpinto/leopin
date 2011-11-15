package com.leopin.parkfifty.server.utils;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;

public abstract class Utils {

	public static String scrubPhoneNum(String phoneNum) {
		return CharMatcher.JAVA_LETTER_OR_DIGIT.retainFrom(Strings.nullToEmpty(phoneNum));
	}
}
