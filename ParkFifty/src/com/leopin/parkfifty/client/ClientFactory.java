package com.leopin.parkfifty.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.leopin.parkfifty.client.services.ParkFiftyService;
import com.leopin.parkfifty.client.views.AppView;
import com.leopin.parkfifty.client.views.AuthHomeView;
import com.leopin.parkfifty.client.views.CompanyRegistrationView;
import com.leopin.parkfifty.client.views.HeaderView;
import com.leopin.parkfifty.client.views.HomeView;

public interface ClientFactory {
	EventBus getEventBus();

	ParkFiftyService getService();
	PlaceController getPlaceController();
	
	AppView getAppView();
	HomeView getHomeView();
	CompanyRegistrationView getCompanyRegistrationView();
	HeaderView getHeaderView();
	AuthHomeView getAuthHomeView();
	
}
