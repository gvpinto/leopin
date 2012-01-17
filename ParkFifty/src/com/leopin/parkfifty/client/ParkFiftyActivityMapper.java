package com.leopin.parkfifty.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.manning.gwtia.ch14.client.mvp.ClientFactory;

public class ParkFiftyActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;
	
	public ParkFiftyActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public Activity getActivity(Place place) {
		// TODO Auto-generated method stub
		return null;
	}

}
