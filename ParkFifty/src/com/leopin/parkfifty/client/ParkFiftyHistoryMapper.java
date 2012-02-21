package com.leopin.parkfifty.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.leopin.parkfifty.client.places.CompanyRegistrationPlace;
import com.leopin.parkfifty.client.places.HomePlace;

/**
 * ParkFiftyHistoryMapper declares all the Places available in ParkFifty
 * @author Glenn Pinto
 *
 */
@WithTokenizers({
	HomePlace.Tokenizer.class,
	CompanyRegistrationPlace.Tokenizer.class
})
public interface ParkFiftyHistoryMapper extends PlaceHistoryMapper {
	// Empty
}
