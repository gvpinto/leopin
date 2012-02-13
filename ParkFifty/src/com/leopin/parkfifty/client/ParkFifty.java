package com.leopin.parkfifty.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.leopin.parkfifty.client.resources.ParkFiftyResources;
import com.leopin.parkfifty.client.views.HomeView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ParkFifty implements EntryPoint {


	ClientFactory clientFactory = GWT.create(ClientFactory.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
	   
		HomeView homeView = clientFactory.getHomeView();
		new ParkFiftyApp(clientFactory.getService(), clientFactory.getEventBus(), homeView);
		RootPanel.get().add(homeView);
		
		// Ensure content resources are initialized
		ParkFiftyResources.INSTANCE.style().ensureInjected();
	}
}
