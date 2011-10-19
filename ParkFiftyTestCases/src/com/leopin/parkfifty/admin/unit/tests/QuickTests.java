package com.leopin.parkfifty.admin.unit.tests;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class QuickTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		Pattern pattern = Pattern.compile("(^[0-9]{10,15}$)|()");
		Matcher matcher = pattern.matcher("9194553262");
		assertTrue(matcher.matches());
		matcher = pattern.matcher("");
		assertTrue(matcher.matches());
		matcher = pattern.matcher("9194553262A");		
		assertFalse(matcher.matches());
		matcher = pattern.matcher("919455326");
		assertFalse(matcher.matches());
//		matcher = pattern.matcher(null);
//		assertFalse(matcher.matches());
		matcher = pattern.matcher(" ");
		assertFalse(matcher.matches());		

	}

}
