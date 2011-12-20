package com.leopin.parkfifty.client;

import com.leopin.parkfifty.client.resources.ParkFiftyResources;
import com.leopin.parkfifty.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ParkFifty implements EntryPoint {


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		MainView mainView = new MainView();
		RootPanel.get().add(mainView);
		ParkFiftyResources.INSTANCE.style().ensureInjected();
	}
}
