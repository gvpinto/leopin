package com.leopin.parkfifty.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class CompanyRegistrationPlace extends Place {
	
	String state;
	public CompanyRegistrationPlace() {
		state = "register";
	}
	
	public String getState() {
		return state;
	}
	
	public static class Tokenizer implements PlaceTokenizer<CompanyRegistrationPlace> {

		@Override
		public CompanyRegistrationPlace getPlace(String token) {
			return new CompanyRegistrationPlace();
		}

		@Override
		public String getToken(CompanyRegistrationPlace place) {
			return place.getState();
		}
		
	}
}
