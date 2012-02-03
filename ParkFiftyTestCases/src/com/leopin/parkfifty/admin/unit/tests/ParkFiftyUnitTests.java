package com.leopin.parkfifty.admin.unit.tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.leopin.parkfifty.shared.utils.AppRegExp;
import com.leopin.parkfifty.shared.utils.Utils;

public class ParkFiftyUnitTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPhoneNum() {
//		fail("Not yet implemented");
		String phoneNum = "9194553262";
		Assert.assertEquals("(919) 455-3262", Utils.formatPhoneNum(phoneNum));
		phoneNum = "19194553262";
		Assert.assertEquals("1(919) 455-3262", Utils.formatPhoneNum(phoneNum));
		phoneNum = "119194553262";
		Assert.assertEquals("11(919) 455-3262", Utils.formatPhoneNum(phoneNum));
		phoneNum = "194553262";
		Assert.assertEquals("(19) 455-3262", Utils.formatPhoneNum(phoneNum));
		phoneNum = "94553262";
		Assert.assertEquals("(9) 455-3262", Utils.formatPhoneNum(phoneNum));
		phoneNum = "4553262";
		Assert.assertEquals("455-3262", Utils.formatPhoneNum(phoneNum));
		phoneNum = "3262";
		Assert.assertEquals("3262", Utils.formatPhoneNum(phoneNum));
		phoneNum = "553262";
		Assert.assertEquals("55-3262", Utils.formatPhoneNum(phoneNum));

	}
	
	@Test
	public void testIsEmptyOrNull() {
		String value = null;
		Assert.assertTrue(Utils.isEmptyOrNull(value));
		value = "";
		Assert.assertTrue(Utils.isEmptyOrNull(value));
		value = "G";
		Assert.assertFalse(Utils.isEmptyOrNull(value));
		value = "Glenn";
		Assert.assertFalse(Utils.isEmptyOrNull(value));
	}
	
	@Test
	public void testValidate() {
		String value = "This is a Parking Company";
		Assert.assertTrue(Utils.validate(value, AppRegExp.COMPANY_NAME, true));
		value = "th";
		Assert.assertFalse(Utils.validate(value, AppRegExp.COMPANY_NAME, true));
	}

}
