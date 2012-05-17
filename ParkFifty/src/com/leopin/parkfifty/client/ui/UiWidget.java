package com.leopin.parkfifty.client.ui;

import com.google.gwt.event.shared.EventHandler;
import com.leopin.parkfifty.client.resources.AppStyles.AppResources;
import com.leopin.parkfifty.client.resources.AppStyles.Style;
import com.leopin.parkfifty.shared.messages.FieldLabels;
import com.leopin.parkfifty.shared.messages.ValidationMessages;

public interface UiWidget {
	
	
	/**
	 * Initialize Handlers
	 * @param handler
	 */
	public void initHandlers(EventHandler handler);
	
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

	
}
