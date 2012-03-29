package com.leopin.parkfifty.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Home Page of the application
 * @author Glenn Pinto
 *
 */
public class HomePlace extends Place {
	
	String token;
	
	public HomePlace() {
		this("init");
	}
	
	public HomePlace(String token){
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
	
//	@Prefix("")
	public static class Tokenizer implements PlaceTokenizer<HomePlace>{

		public HomePlace getPlace(String token) {
			return new HomePlace(token);
		}

		public String getToken(HomePlace place) {
			return place.getToken();
		}
	}

}
