package com.leopin.parkfifty.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.leopin.parkfifty.client.views.AppView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ParkFifty implements EntryPoint {


	ClientFactory clientFactory = GWT.create(ClientFactory.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
	   
		AppView appView = clientFactory.getAppView();
		ParkFiftyApp app = new ParkFiftyApp(clientFactory.getService(), clientFactory.getEventBus(), clientFactory.getAppView());
		RootPanel.get().add(appView);
		
//		// Ensure content resources are initialized
//		ParkFiftyResources.INSTANCE.style().ensureInjected();
		
	}
}
