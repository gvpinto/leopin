package com.leopin.parkfifty.client.presenters;

import com.leopin.parkfifty.client.domain.CompanyProxy;

public interface HomePresenter extends Presenter {
	public boolean validator(String value, String regex, boolean isRequired);
	/**
	 * Validate Company Name
	 * @param value value to be validated
	 * @return true is the value passes validation and false if it fails
	 */
	public boolean validateName(String value);
	
	/**
	 * Validate URL
	 * @param value value to be validated
	 * @return true is the value passes validation and false if it fails
	 */
	public boolean validateUrl(String value);
	
	/**
	 * Validate Email Address
	 * @param value value to be validated
	 * @return true is the value passes validation and false if it fails
	 */
	public boolean validateEmail(String value);
	
	/**
	 * Validate Primary Phone
	 * @param value value to be validated
	 * @return true is the value passes validation and false if it fails
	 */
	public boolean validatePriPhone(String value);
	
	/**
	 * Validate Secondary Phone
	 * @param value value to be validated
	 * @return true is the value passes validation and false if it fails
	 */
	public boolean validateSecPhone(String value);
	
	/**
	 * Validate Other Phone - Secondary or Fax
	 * @param value value to be validated
	 * @return true is the value passes validation and false if it fails
	 */
	public boolean validateOtherPhone(String value);
	
	/**
	 * General Validation
	 * @param name name of the variable to be validated
	 * @param value value to be validated
	 * @return true if the value passes the validation else return false
	 */
	public boolean validate(String name, String value);
	
	/**
	 * Strip all special characters - mostly used to clean a phone number
	 * @param value
	 * @return clean value
	 */
	public String stripChars(String value);
	
	/**
	 * Format a phone number as (xxx) xxx-xxxx
	 * @param value phone numbers in pure digits
	 * @return formatted phone number
	 */
	public String formatPhoneNum(String value);
	public void next(CompanyProxy company);
	public void onFocus(String name, String text);
}
