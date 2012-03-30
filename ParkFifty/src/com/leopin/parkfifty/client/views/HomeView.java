package com.leopin.parkfifty.client.views;

public interface HomeView extends View {

	// public void showHelpUiName(String text);
	//
	// public void showHelpUiUrl(String text);
	//
	// public void showHelpUiEmail(String text);
	//
	// public void showHelpUiPriPhone(String text);
	//
	// public void showHelpUiSecPhone(String text);
	//
	// public void showHelpUiFax(String text);

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

	/**
	 * Sets the formatted phone number back into the ui field
	 * applicable for Primary phone number, Secondary phone number and Fax
	 * @param name Name of the widget whose value had to be reset to the formatted value
	 * @param value Unformatted phone number
	 */
	void setUiText(String name, String text);

	/**
	 * Selects all the contents and then sets the focus on the widget
	 * @param name Widget name
	 */
	void setFocus(String name);

	/**
	 * Clear/reset all the widgets
	 */
	public void clear();

}
