package com.leopin.parkfifty.client.presenters;

import com.leopin.parkfifty.shared.domain.CompanyProxy;

public interface HomePresenter extends Presenter {
//	public boolean validator(String value, String regex, boolean isRequired);
//	/**
//	 * Validate Company Name
//	 * @param value value to be validated
//	 * @return true is the value passes validation and false if it fails
//	 */
//	public boolean validateName(String value);
//	
//	/**
//	 * Validate URL
//	 * @param value value to be validated
//	 * @return true is the value passes validation and false if it fails
//	 */
//	public boolean validateUrl(String value);
//	
//	/**
//	 * Validate Email Address
//	 * @param value value to be validated
//	 * @return true is the value passes validation and false if it fails
//	 */
//	public boolean validateEmail(String value);
//	
//	/**
//	 * Validate Primary Phone
//	 * @param value value to be validated
//	 * @return true is the value passes validation and false if it fails
//	 */
//	public boolean validatePriPhone(String value);
//
//	
//	/**
//	 * Validate Other Phone - Secondary or Fax
//	 * @param value value to be validated
//	 * @return true is the value passes validation and false if it fails
//	 */
//	public boolean validateOtherPhone(String value);
//	
	/**
	 * General Validation
	 * @param name name of the variable to be validated
	 * @param value value to be validated
	 * @return true if the value passes the validation else return false
	 */
	public boolean validate(String name, String value);
	public String next(CompanyProxy company);
	public void onFocusSetValue(String name, String text);
}
