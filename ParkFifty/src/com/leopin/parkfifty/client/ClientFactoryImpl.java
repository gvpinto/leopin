package com.leopin.parkfifty.client;


import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.leopin.parkfifty.client.services.ParkFiftyService;
import com.leopin.parkfifty.client.ui.CompanyWidget;
import com.leopin.parkfifty.client.views.AppView;
import com.leopin.parkfifty.client.views.AppViewImpl;
import com.leopin.parkfifty.client.views.CompanyRegistrationView;
import com.leopin.parkfifty.client.views.CompanyRegistrationViewImpl;
import com.leopin.parkfifty.client.views.HeaderView;
import com.leopin.parkfifty.client.views.HeaderViewImpl;
import com.leopin.parkfifty.client.views.HomeView;
import com.leopin.parkfifty.client.views.HomeViewImpl;

public class ClientFactoryImpl implements ClientFactory {

	private static EventBus eventBus;
	private static AppView appView;
	private static ParkFiftyService service;
	private static PlaceController placeController;
	private static HomeView homeView;
	private static CompanyWidget companyWidget;
	private static CompanyRegistrationView companyRegistrationView;
	private static HeaderView headerView;
	
	public EventBus getEventBus() {
		if (eventBus == null) eventBus = new SimpleEventBus();
		return eventBus;
	}

	public PlaceController getPlaceController(){
		if (placeController == null) placeController = new PlaceController(getEventBus());
		return placeController;
	}
	
	public HomeView getHomeView() {
		if (homeView == null) homeView = new HomeViewImpl();
		return homeView;
	}
	
	public CompanyWidget getCompanyWidget() {
		if (companyWidget == null) companyWidget = new CompanyWidget();
		return companyWidget;
	}

	@Override
	public ParkFiftyService getService() {
		if (service == null) service = new ParkFiftyService();
		return service;
	}

	@Override
	public AppView getAppView() {
		if (appView == null) appView = new AppViewImpl();
		return appView;
	}


	@Override
	public CompanyRegistrationView getCompanyRegistrationView() {
		if (companyRegistrationView == null) companyRegistrationView = new CompanyRegistrationViewImpl();
		return companyRegistrationView;
	}

	@Override
	public HeaderView getHeaderView() {
		if (headerView == null) headerView = new HeaderViewImpl();
		return headerView;
	}

}
