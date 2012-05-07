package com.leopin.parkfifty.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.leopin.parkfifty.client.activities.AuthHeaderActivity;
import com.leopin.parkfifty.client.activities.HeaderActivity;
import com.leopin.parkfifty.client.places.AuthHomePlace;
import com.leopin.parkfifty.client.places.HomePlace;

public class HeaderActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;
	
	public HeaderActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace) {
			return new HeaderActivity((HomePlace) place, this.clientFactory);
//		} else if (place instanceof CompanyRegistrationPlace) {
//			return new CompanyRegistrationActivity((CompanyRegistrationPlace) place, this.clientFactory);
		} else if(place instanceof AuthHomePlace) {
			return new AuthHeaderActivity((AuthHomePlace) place, this.clientFactory);
		} else {
			return null;
		}
		
	}
}
