package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class CompanyOwnerWidget extends Composite {

	private static CompanyOwnerWidgetUiBinder uiBinder = GWT
			.create(CompanyOwnerWidgetUiBinder.class);

	interface CompanyOwnerWidgetUiBinder extends
			UiBinder<Widget, CompanyOwnerWidget> {
	}

	public CompanyOwnerWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	public CompanyOwnerWidget(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}


}
