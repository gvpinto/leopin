package com.leopin.parkfifty.server.constraints;


import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.leopin.parkfifty.server.constraints.imp.CheckGeoLocatorValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckGeoLocatorValidator.class)
@Documented
public @interface CheckGeoLocator {
    String message() default "{geo.locator.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
	/**
	 * Defines several {@code @CheckGeoLocator} annotations on the same element.
	 */
	@Target( { METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		CheckGeoLocator[] value();
	}
}
