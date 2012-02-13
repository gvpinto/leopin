package com.leopin.parkfifty.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Home Page of the application
 * @author Glenn Pinto
 *
 */
public class HomePlace extends Place {
	
	public HomePlace(){
	}

	public static class Tokenizer implements PlaceTokenizer<HomePlace>{

		public HomePlace getPlace(String token) {
			return new HomePlace();
		}

		public String getToken(HomePlace place) {
			return null;
		}
	}
}
