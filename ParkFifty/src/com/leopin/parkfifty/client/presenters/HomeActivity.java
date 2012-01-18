package com.leopin.parkfifty.client.presenters;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.leopin.parkfifty.client.ClientFactory;
import com.leopin.parkfifty.client.places.HomePlace;
import com.leopin.parkfifty.client.views.MainView;

public class HomeActivity extends AbstractActivity implements HomePresenter {
	
	
	private ClientFactory clientFactory;
	private MainView mainView;
	
	public HomeActivity(HomePlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		// TODO Auto-generated method stub

	}

}
