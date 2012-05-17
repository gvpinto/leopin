package com.leopin.parkfifty.client.ui;

import com.leopin.parkfifty.shared.messages.FieldLabels;
import com.leopin.parkfifty.shared.messages.ValidationMessages;

/**
 * All widgets implementing form functionality should implement this interface
 * @author Glenn Pinto
 *
 */
public interface UiFormWidget {
	
	/**
	 * Returns application wide validation messages for displaying messages for fields
	 * whose data value entered by the user has failed
	 * @return validationMessages message object
	 */
	ValidationMessages validationMessages();
	
	/**
	 * Returns Field Labels. Positioned the app to support multiple languages
	 * @return
	 */
	FieldLabels fieldLabels();
	
	/**
	 * Hide all help Texts on the form
	 */
	void hideHelpTexts();
}
