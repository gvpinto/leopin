package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class AuthMenu extends Composite  {

	LIElement lastActiveMenu;
	private static AuthMenuUiBinder uiBinder = GWT
			.create(AuthMenuUiBinder.class);

	interface AuthMenuUiBinder extends UiBinder<Widget, AuthMenu> {
	}

	public AuthMenu() {
		initWidget(uiBinder.createAndBindUi(this));
		uiMainNav.getElement().setId("uiMainNav");
		activateLocationsMenu();
		//uiMainNavLocations.getStyle().setDisplay(Display.NONE);
	}


	@UiField
	HTMLPanel uiMainNav;
	
	@UiField
	LIElement uiMainNavLocations;
	
	@UiField
	LIElement uiMainNavDiscounts;
	
	@UiField
	LIElement uiMainNavRates;
	
	@UiField
	LIElement uiMainNavAccount;
	
	
	public void displayLocationsMenu(boolean show) {
		if (show) {
			uiMainNavLocations.getStyle().setDisplay(Display.BLOCK);
		} else {
			uiMainNavLocations.getStyle().setDisplay(Display.NONE);
		}
	}
	
	public void displayDiscountsMenu(boolean show) {
		if (show) {
			uiMainNavDiscounts.getStyle().setDisplay(Display.BLOCK);
		} else {
			uiMainNavDiscounts.getStyle().setDisplay(Display.NONE);
		}
	}

	public void displayRatesMenu(boolean show) {
		if (show) {
			uiMainNavRates.getStyle().setDisplay(Display.BLOCK);
		} else {
			uiMainNavRates.getStyle().setDisplay(Display.NONE);
		}
	}

	public void displayAccountMenu(boolean show) {
		if (show) {
			uiMainNavAccount.getStyle().setDisplay(Display.BLOCK);
		} else {
			uiMainNavAccount.getStyle().setDisplay(Display.NONE);
		}
	}

	public void activateLocationsMenu() {
		uiMainNavLocations.setClassName("active");
		deactivateMenu();
	}
	
	public void activateDiscountsMenu() {
		uiMainNavDiscounts.setClassName("active");
		deactivateMenu();
	}
	
	public void activateRatesMenu() {
		uiMainNavRates.setClassName("active");
		deactivateMenu();
	}
	
	public void activateAccountMenu() {
		uiMainNavAccount.setClassName("active");
		deactivateMenu();
	}
	
	private void deactivateMenu() {
		if (lastActiveMenu != null) {
			lastActiveMenu.removeClassName("active");
		}
	}
	
}
