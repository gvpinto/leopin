package com.leopin.parkfifty.server.constraints.imp;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.leopin.parkfifty.server.constraints.CheckGeoLocator;

public class CheckGeoLocatorValidator implements ConstraintValidator<CheckGeoLocator, BigDecimal> {

	@Override
	public void initialize(CheckGeoLocator checkGeoLocator) {
		
	}

	@Override
	public boolean isValid(BigDecimal geoCode, ConstraintValidatorContext constraintValidatorContext) {
		
		if (geoCode == null) {
			return true;
		}
		
		return false;
	}

}
