package com.leopin.parkfifty.client.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.leopin.parkfifty.client.ClientFactory;
import com.leopin.parkfifty.client.places.CompanyRegistrationPlace;
import com.leopin.parkfifty.client.presenters.CompanyRegistrationPresenter;
import com.leopin.parkfifty.client.views.CompanyRegistrationView;

public class CompanyRegistrationActivity extends AbstractActivity implements
		CompanyRegistrationPresenter {

	CompanyRegistrationView companyRegistrationView;
	ClientFactory clientFactory;
	EventBus eventBus;
	
	public CompanyRegistrationActivity(CompanyRegistrationPlace place,
			ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.companyRegistrationView = clientFactory.getCompanyRegistrationView();
		
	}

	@Override
	public void goTo(Place place) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.eventBus = eventBus;
		panel.setWidget(this.companyRegistrationView);
		bind();
	}

	/**
	 * Bind the presenter and events
	 */
	private void bind() {
		this.companyRegistrationView.setPresenter(this);
	}

}
