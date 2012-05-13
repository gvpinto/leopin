package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

public class TextBoxCombo extends TextBoxBaseCombo {

	private static TextBoxComboUiBinder uiBinder = GWT.create(TextBoxComboUiBinder.class);
	
	interface TextBoxComboUiBinder extends UiBinder<Widget, TextBoxCombo> {
	}
	
	public TextBoxCombo() {
		initWidget(uiBinder.createAndBindUi(this));
		this.getUiHelpText().setVisible(false);
	}
	
}
