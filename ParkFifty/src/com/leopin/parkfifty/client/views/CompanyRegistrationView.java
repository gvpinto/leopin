package com.leopin.parkfifty.client.views;

import com.leopin.parkfifty.shared.domain.CompanyProxy;

public interface CompanyRegistrationView extends View {

	/**
	 * Set the company information of the Company widget from the domain object 
	 * @param company Company information
	 */
	void setCompany(CompanyProxy company);

	/**
	 * Sets the formatted phone number back into the ui field
	 * applicable for Primary phone number, Secondary phone number and Fax
	 * @param name Name of the widget whose value had to be reset to the formatted value
	 * @param value Unformatted phone number
	 */
	void setUiText(String name, String value);

	/**
	 * Display the help icon and the text that appears on hover over
	 * when a validation error occurs
	 * @param name Name of the TextBoxCombo Widget
	 */
	void showHelp(String name);

	/**
	 * Remove the help icon and the hover over text when the validation passes
	 * @param name Name of the TextBoxCombo Widget
	 */
	void removeHelp(String name);
}
