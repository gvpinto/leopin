package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

public class PasswordCombo extends TextBoxBaseCombo {

	private static PasswordComboUiBinder uiBinder = GWT
			.create(PasswordComboUiBinder.class);

	interface PasswordComboUiBinder extends UiBinder<Widget, PasswordCombo> {
	}

	public PasswordCombo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
