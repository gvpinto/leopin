package com.leopin.parkfifty.client.views;

public interface HeaderView extends View {
	
	/**
	 * Display an Error message if any error occurs after an authentication attempt
	 * @param message
	 */
	void setErrorMsg(String message);
	
	/**
	 * Display a successful text if the authentication is successful
	 * @param loginSuccessful 
	 */
	void setLoginText(boolean loginSuccessful);

	/**
	 * remove any help and icon than might have appeared due to an validation error
	 * @param name Name of the field
	 */
	void removeHelp(String name);

	/**
	 * display the help icon and text when a validation fails
	 * @param name Name of the field
	 */
	void showHelp(String name);
	
}
