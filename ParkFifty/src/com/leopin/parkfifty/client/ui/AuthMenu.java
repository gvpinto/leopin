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

public class AuthMenu extends Composite  {

	private static AuthMenuUiBinder uiBinder = GWT
			.create(AuthMenuUiBinder.class);

	interface AuthMenuUiBinder extends UiBinder<Widget, AuthMenu> {
	}

	public AuthMenu() {
		initWidget(uiBinder.createAndBindUi(this));
	}




}
