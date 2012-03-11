package com.leopin.parkfifty.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.leopin.parkfifty.shared.domain.CompanyProxy;

public class CompanyRegistrationPlace extends Place {
	
	String state;
	CompanyProxy company;
	
	public CompanyRegistrationPlace(CompanyProxy company) {
		state = "register";
		company = company;
	}
	
	public String getState() {
		return state;
	}
	
	public static class Tokenizer implements PlaceTokenizer<CompanyRegistrationPlace> {

		@Override
		public CompanyRegistrationPlace getPlace(String token) {
			return new CompanyRegistrationPlace(null);
		}

		@Override
		public String getToken(CompanyRegistrationPlace place) {
			return place.getState();
		}
		
	}
}
