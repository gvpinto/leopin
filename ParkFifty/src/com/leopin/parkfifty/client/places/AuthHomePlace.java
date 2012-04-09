package com.leopin.parkfifty.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AuthHomePlace extends Place {
	String token;
	
	public AuthHomePlace() {
		this("init");
	}
	
	public AuthHomePlace(String token){
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
	
//	@Prefix("")
	public static class Tokenizer implements PlaceTokenizer<AuthHomePlace>{

		public AuthHomePlace getPlace(String token) {
			return new AuthHomePlace(token);
		}

		public String getToken(AuthHomePlace place) {
			return place.getToken();
		}
	}
}
