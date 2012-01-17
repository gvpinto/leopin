package com.leopin.parkfifty.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory {
	public EventBus getEventBus();
	PlaceController getPlaceController();
}
