package com.leopin.parkfifty.admin.tests.unit.server;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.leopin.parkfifty.poc.Car;
import com.leopin.parkfifty.poc.TestValidation;
import com.leopin.parkfifty.shared.utils.AppRegExp;
import com.leopin.parkfifty.shared.utils.Utils;

public class AdminUnitTests {

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
	
	@Test
	public void testHibernateValidation() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Car car = new Car(null);

		Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);

		assertEquals(1, constraintViolations.size());
		assertEquals("may not be null", constraintViolations.iterator().next().getMessage());		
	}

	@Test
	public void testValidation() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		TestValidation tv = new TestValidation();
		tv.setEmail("gpinto@gmail.com");
		tv.setPhone("");
		tv.setUrl("http://www.bbt.com");

		Set<ConstraintViolation<TestValidation>> constraintViolations = validator.validate(tv);

		assertEquals(1, constraintViolations.size());
//		assertEquals("may not be null", constraintViolations.iterator().next().getMessage());		
	}

	@Test
	public void testValidationWithLocalValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();		
		
		TestValidation tv = new TestValidation();
		tv.setEmail("gpinto@gmail.com");
		tv.setPhone("9194553262qwe");
		tv.setUrl("http://www.bbt.com");

		Set<ConstraintViolation<TestValidation>> constraintViolations = validator.validate(tv);

		assertEquals(1, constraintViolations.size());
//		assertEquals("may not be null", constraintViolations.iterator().next().getMessage());		
	}

	@Test
	public void testValidationWithValidationFactory() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		TestValidation tv = new TestValidation();
		tv.setEmail("gpinto@gmail.com");
		tv.setPhone("9194553262qwe");
		tv.setUrl("http://www.bbt.com");

		Set<ConstraintViolation<TestValidation>> constraintViolations = validator.validate(tv);

		assertEquals(1, constraintViolations.size());
//		assertEquals("may not be null", constraintViolations.iterator().next().getMessage());		
	}
	
	@Test
	public void testBigDecimal() {
		
		BigDecimal value = new BigDecimal("0.00");
		Assert.assertNotNull(value);
		Assert.assertEquals(new BigDecimal("0.00"), value);
		
		
	}

}
