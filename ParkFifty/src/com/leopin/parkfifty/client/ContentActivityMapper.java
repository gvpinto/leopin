package com.leopin.parkfifty.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.leopin.parkfifty.client.activities.CompanyRegistrationActivity;
import com.leopin.parkfifty.client.activities.HomeActivity;
import com.leopin.parkfifty.client.places.CompanyRegistrationPlace;
import com.leopin.parkfifty.client.places.HomePlace;

public class ContentActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;
	
	public ContentActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace) {
			return new HomeActivity((HomePlace) place, this.clientFactory);
		} else if (place instanceof CompanyRegistrationPlace) {
			return new CompanyRegistrationActivity((CompanyRegistrationPlace) place, this.clientFactory);
		} else {
			return null;
		}
	}

}
