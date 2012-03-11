package com.leopin.parkfifty.client.ui;

import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.leopin.parkfifty.client.resources.ParkFiftyResources;

public interface UiWidget {
	
	/**
	 * Initialize the widgets to handle onBlur events
	 * @param handler Blur Handler
	 */
	public void initFocusHandlers(FocusHandler handler);
	
	/**
	 * Initialize the widgets to handle onFocus events
	 * @param handler
	 */
	public void initBlurHandlers(BlurHandler handler);
	
	/**
	 * Return the Global style resource
	 * @return
	 */
	ParkFiftyResources.Style style();
}
