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
		Assert.assertTrue(pattern.matcher("glenn-pinto@gmail.com").matches());
		Assert.assertTrue(pattern.matcher("glenn-pinto@gmail.com.in").matches());
		
		Assert.assertFalse(pattern.matcher("gv pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("12glenn+pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("12glenn$pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("glenn..pinto@gmailcom").matches());
		Assert.assertFalse(pattern.matcher("glenn.pinto!@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("glenn$pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("glenn{pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("glenn}pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("glenn*pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("glenn'pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("glenn#pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("glenn!pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("glenn%pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("glenn&pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("glenn%pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("glenn^pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("glenn=pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("glenn/pinto@gmail.com").matches());
		Assert.assertFalse(pattern.matcher("glennpinto@gmail-com").matches());
		Assert.assertFalse(pattern.matcher("glennpinto@gmail_com").matches());
		Assert.assertFalse(pattern.matcher("glennpinto@gmailcom").matches());

	}

	public void testURL() {
		
		Pattern pattern = Pattern.compile(AppRegExp.URL);
		Assert.assertTrue(pattern.matcher("http://www.bbt.com").matches());
		Assert.assertTrue(pattern.matcher("https://www.bbt.com").matches());
		Assert.assertTrue(pattern.matcher("https://www.bbt.com/hello/hello.html").matches());
		Assert.assertTrue(pattern.matcher("https://bbt.com").matches());
		Assert.assertTrue(pattern.matcher("https://bbt-hello.com").matches());
		Assert.assertTrue(pattern.matcher("https://www.bbt-hello.com").matches());
		Assert.assertTrue(pattern.matcher("http://www.bbt-hello.com").matches());
		Assert.assertFalse(pattern.matcher("http://www.bbt-hello.com/javascript:alert(&quot;XSS&quot;)").matches());
		Assert.assertFalse(pattern.matcher("http://www.bbt-hello.com/hello?<SCRIPT>alert(“Cookie”+document.cookie)</SCRIPT>").matches());
		Assert.assertFalse(pattern.matcher("http://www.bbt-hello.com/hello?alert(“Cookie”+document.cookie)").matches());
		Assert.assertFalse(pattern.matcher("http://www.example.com/malicious-code.js%3e%3c/script%3e").matches());
		
		
	}
	
	public void testGeoCode() {
		Pattern pattern = Pattern.compile(AppRegExp.GEO_CD);
		Assert.assertTrue(pattern.matcher("-77.2341232").matches());
		Assert.assertTrue(pattern.matcher("-452.2341232").matches());
		Assert.assertTrue(pattern.matcher("77.2341232").matches());
		Assert.assertTrue(pattern.matcher("452.2341232").matches());
		
		Assert.assertFalse(pattern.matcher("-7754.2341232").matches());
		Assert.assertFalse(pattern.matcher("-452.23412322").matches());
		Assert.assertFalse(pattern.matcher("7754.2341232").matches());
		Assert.assertFalse(pattern.matcher("452.234123").matches());
		Assert.assertFalse(pattern.matcher("-452.234123").matches());
		Assert.assertFalse(pattern.matcher("-4522.234123").matches());
		
	}
	
	@Test
	public void testParkFacilityType() {
		Pattern pattern = Pattern.compile(AppRegExp.PARK_FACILITY_TYPE);
		Assert.assertTrue(pattern.matcher("C").matches());
		Assert.assertTrue(pattern.matcher("O").matches());
		Assert.assertTrue(pattern.matcher("CM").matches());
		Assert.assertTrue(pattern.matcher("S").matches());
		
		Assert.assertFalse(pattern.matcher("X").matches());
		Assert.assertFalse(pattern.matcher("c").matches());
		Assert.assertFalse(pattern.matcher("o").matches());
		Assert.assertFalse(pattern.matcher("cm").matches());
		Assert.assertFalse(pattern.matcher("s").matches());
		Assert.assertFalse(pattern.matcher("x").matches());

	}
	
	@Test
	public void testCompanyCode() {
		Pattern pattern = Pattern.compile(AppRegExp.COMPANY_CODE);
		Assert.assertTrue(pattern.matcher("GLEN_PINTO").matches());
		Assert.assertTrue(pattern.matcher("GLEN-PINTO").matches());
		Assert.assertTrue(pattern.matcher("GLEN123T4O").matches());
		Assert.assertTrue(pattern.matcher("GLENPINTO").matches());
		
		Assert.assertFalse(pattern.matcher("GLEN PINTO").matches());
		Assert.assertFalse(pattern.matcher("1GLENPINTO").matches());
		Assert.assertFalse(pattern.matcher("GLEN@PINTO").matches());
		Assert.assertFalse(pattern.matcher("GLENNPINTOC").matches());
		Assert.assertFalse(pattern.matcher("").matches());

	}	
	
	@Test
	public void testLocationDesc() {
		Pattern pattern = Pattern.compile(AppRegExp.LOCATION_DESC);
		Assert.assertTrue(pattern.matcher("This is a Test of a good match. I de-finitely want to under_score the reason, that this is the right thing to do.").matches());
	}
	
	@Test
	public void testStreet() {
		Pattern pattern = Pattern.compile(AppRegExp.STREET);
		Assert.assertTrue(pattern.matcher("12808 Baybriar Dr, Ste 200").matches());
		Assert.assertTrue(pattern.matcher("12808 Baybriar Dr, Ste-200").matches());
		Assert.assertTrue(pattern.matcher("12808 Baybriar Dr, Ste'200").matches());
		Assert.assertTrue(pattern.matcher("12808 Baybriar Dr, Ste'200.").matches());
		Assert.assertTrue(pattern.matcher("1280").matches());
		
		Assert.assertFalse(pattern.matcher("12808 Baybriar_Dr, Ste 200").matches());
		Assert.assertFalse(pattern.matcher("12808 BaybriarDr; Ste 200").matches());
		Assert.assertFalse(pattern.matcher("12808 Baybriar Dr, Ste200 How Do you do and are you fine An fine ").matches());
		Assert.assertFalse(pattern.matcher("128").matches());
		Assert.assertFalse(pattern.matcher("").matches());

	}	
}
