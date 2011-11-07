package com.leopin.parkfifty.admin.unit.tests;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.leopin.parkfifty.shared.AppRegExp;

public class RegexpUnitTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCompanyName() {
//		fail("Not yet implemented");
		Pattern pattern = Pattern.compile(AppRegExp.COMPANY_NAME);
		Assert.assertTrue(pattern.matcher("This is a valid company name").matches());
		Assert.assertTrue(pattern.matcher("This is a Good Company Nam'e. But I need to Find_out a Company; Name that-matches, such a long name.").matches());
		Assert.assertFalse(pattern.matcher("This is a Good Company Name. But I need to Find_out a Company; Name that-matches, such a long name. A").matches());
		Assert.assertFalse(pattern.matcher("Th").matches());
		Assert.assertTrue(pattern.matcher("The").matches());
		Assert.assertFalse(pattern.matcher("This is a Good Company Nam'e. But I need to Find_out a Company+ Name that-matches, such a long name.").matches());
		Assert.assertFalse(pattern.matcher("This is a Good Company Nam'e. But I need to Find_out a Company< Name that-matches, such a long name.").matches());
	}
	
	@Test
	public void testEmail() {
//		fail("Not yet implemented");
		Pattern pattern = Pattern.compile(AppRegExp.EMAIL);
		Assert.assertTrue(pattern.matcher("gvpinto@gmail.com").matches());
		Assert.assertTrue(pattern.matcher("glenn.pinto@gmail.com").matches());
		Assert.assertTrue(pattern.matcher("12glennpinto@gmail.com").matches());
		Assert.assertTrue(pattern.matcher("12glenn.pinto@gmail.com").matches());
		Assert.assertTrue(pattern.matcher("12glenn-pinto@gmail.com").matches());
	}

}
