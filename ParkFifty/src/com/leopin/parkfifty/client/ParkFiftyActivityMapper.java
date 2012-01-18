package com.leopin.parkfifty.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.leopin.parkfifty.client.places.HomePlace;
import com.leopin.parkfifty.client.presenters.HomeActivity;

public class ParkFiftyActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;
	
	public ParkFiftyActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace) {
			return new HomeActivity((HomePlace) place, this.clientFactory);
		} else {
			return null;
		}
	}

}
