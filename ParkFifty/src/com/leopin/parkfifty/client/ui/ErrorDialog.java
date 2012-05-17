package com.leopin.parkfifty.client.ui;

import com.google.gwt.place.shared.Place;
import com.leopin.parkfifty.client.ClientFactory;

/**
 * This dialog box is display for any failed actions
 * @author Glenn Pinto
 */
public class ErrorDialog extends ConfirmationDialog {

	public ErrorDialog(ClientFactory clientFactory) {
		super(clientFactory, true);
	}
	
}
