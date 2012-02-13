package com.leopin.parkfifty.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class CompanySignUpPlace extends Place {
	
	public CompanySignUpPlace() {
		
	}
	
	public static class Tokenizer implements PlaceTokenizer<CompanySignUpPlace> {

		@Override
		public CompanySignUpPlace getPlace(String token) {
			return new CompanySignUpPlace();
		}

		@Override
		public String getToken(CompanySignUpPlace place) {
			return null;
		}
		
	}
}
