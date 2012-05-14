package com.leopin.parkfifty.client.views;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.presenters.Presenter;
import com.leopin.parkfifty.client.resources.AppStyles.AppResources;
import com.leopin.parkfifty.client.resources.AppStyles.Style;
import com.leopin.parkfifty.shared.messages.FieldLabels;
import com.leopin.parkfifty.shared.messages.ValidationMessages;

public interface View extends IsWidget {
	
	/**
	 * Set the Presenter and initialize the View
	 * @param presenter
	 */
	void setPresenter(Presenter presenter);
	
	/**
	 * Return the view
	 */
	Widget asWidget();
	
	/**
	 * Returns the Application wide style resource
	 * @return
	 */
	Style style();
	
	/**
	 * Returns the application wide file, css, image etc resources
	 * @return
	 */
	AppResources resources();
	
	/**
	 * Returns application wide validation messages for displaying messages for fields
	 * whose data value entered by the user has failed
	 * @return validationMessages message object
	 */
	ValidationMessages validationMessages();
	
	/**
	 * Return Field Labels and Titles
	 */
	FieldLabels fieldLabels();
	
}
