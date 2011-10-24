package com.leopin.parkfifty.admin.unit.tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.leopin.parkfifty.poc.Car;
import com.leopin.parkfifty.poc.TestValidation;

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
}
