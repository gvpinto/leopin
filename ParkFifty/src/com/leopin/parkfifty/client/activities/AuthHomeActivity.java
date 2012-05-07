package com.leopin.parkfifty.client.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.leopin.parkfifty.client.ClientFactory;
import com.leopin.parkfifty.client.places.AuthHomePlace;
import com.leopin.parkfifty.client.presenters.AuthHomePresenter;
import com.leopin.parkfifty.client.views.AuthHomeView;
import com.leopin.parkfifty.shared.messages.AppMessages;

public class AuthHomeActivity extends AbstractActivity implements
		AuthHomePresenter {

	ClientFactory clientFactory;
	EventBus eventBus;
	AuthHomeView view;
	
	public AuthHomeActivity(AuthHomePlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.view = clientFactory.getAuthHomeView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.eventBus = eventBus;
		panel.setWidget(this.view);
		bind();
	}

	@Override
	public void manageLocations() {
		// TODO Auto-generated method stub

	}

	@Override
	public void manageLocationRates() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gotToHomePage() {
		// TODO Auto-generated method stub

	}

	@Override
	public void listAllLocations() {
		// TODO Auto-generated method stub

	}

	@Override
	public void goTo(Place place) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bind() {
		this.view.setPresenter(this);
		
	}

	
	@Override
	public AppMessages messages() {
		return AppMessages.INSTANCE;
	}

}
