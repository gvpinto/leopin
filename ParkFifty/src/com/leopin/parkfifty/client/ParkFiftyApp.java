package com.leopin.parkfifty.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.web.bindery.event.shared.EventBus;
import com.leopin.parkfifty.client.services.ParkFiftyService;

public class ParkFiftyApp {

	EventBus eventBus;
	ClientFactory clientFactory = GWT.create(ClientFactory.class);
	Label busyLabel = new Label("BUSY");
	// Define the JSONP Service Class
	private Place defaultPlace = new HomePlace();
	
	public ParkFiftyApp(
			ParkFiftyService service, EventBus eventBus, AcceptsOneWidget widget) {
		
	}

}
