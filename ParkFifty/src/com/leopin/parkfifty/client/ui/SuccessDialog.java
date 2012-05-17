package com.leopin.parkfifty.client.ui;

import com.google.gwt.place.shared.Place;
import com.leopin.parkfifty.client.ClientFactory;

/**
 * This dialog box is display for any successfully actions
 * @author Glenn Pinto
 */
public class SuccessDialog extends ConfirmationDialog {

	public SuccessDialog(ClientFactory clientFactory) {
		super(clientFactory, false);
	}

}
