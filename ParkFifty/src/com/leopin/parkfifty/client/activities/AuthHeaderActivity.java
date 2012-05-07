package com.leopin.parkfifty.client.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.leopin.parkfifty.client.ClientFactory;
import com.leopin.parkfifty.client.places.AuthHomePlace;
import com.leopin.parkfifty.client.presenters.AuthHeaderPresenter;
import com.leopin.parkfifty.client.views.HeaderView;
import com.leopin.parkfifty.shared.messages.AppMessages;

public class AuthHeaderActivity extends AbstractActivity implements
		AuthHeaderPresenter {

	ClientFactory clientFactory;
	HeaderView view;
	EventBus eventBus;
	
	public AuthHeaderActivity(AuthHomePlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.view = clientFactory.getHeaderView();
	}

	@Override
	public void goTo(Place place) {

	}

	@Override
	public void bind() {
		this.view.setPresenter(this);
	}

	@Override
	public AppMessages messages() {
		return null;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		bind();
		this.eventBus = eventBus;
		this.view.setLoginText(true);
	}

}
