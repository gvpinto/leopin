package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CompanyWidget extends Composite {

	private static CompanyWidgetUiBinder uiBinder = GWT
			.create(CompanyWidgetUiBinder.class);

	interface CompanyWidgetUiBinder extends UiBinder<Widget, CompanyWidget> {
	}

	
	public CompanyWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	// Had to rename the button field as continue is a key fiels
	@UiField
	Button uiContinue;

	@UiHandler("uiContinue")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

}
