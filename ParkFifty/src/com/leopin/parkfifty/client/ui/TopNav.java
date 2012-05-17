package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.resources.AppStyles;
import com.leopin.parkfifty.client.resources.AppStyles.AppResources;
import com.leopin.parkfifty.client.resources.AppStyles.Style;
import com.leopin.parkfifty.shared.messages.FieldLabels;
import com.leopin.parkfifty.shared.messages.ValidationMessages;

public class TopNav extends Composite implements UiWidget { //implements HasClickHandlers {

	private static TopNavUiBinder uiBinder = GWT.create(TopNavUiBinder.class);

	interface TopNavUiBinder extends UiBinder<Widget, TopNav> {
	}

	public TopNav() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Anchor uiLogin;
	public Anchor getUiLogin() {
		return uiLogin;
	}

	public TopNav(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setToLogOff() {
		getUiLogin().setText("Log Off");
	}
	
	
	/**
	 * Set the Anchor to "Login" to indicate the client needs to login
	 */
	public void setToLogin() {
		getUiLogin().setText("Login");
	}

	@Override
	public void initHandlers(EventHandler handler) {
		uiLogin.addClickHandler((ClickHandler)handler);
	}

	@Override
	public Style style() {
		return AppStyles.style();
	}
	
	@Override
	public AppResources resources() {
		return AppStyles.resources();
	}

	
//	@Override
//	public HandlerRegistration addClickHandler(ClickHandler handler) {
//		//return addDomHandler(handler, ClickEvent.getType());
//		return getUiLogin().addClickHandler(handler);
//	}

}
