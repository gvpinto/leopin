package com.leopin.parkfifty.client.views;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.presenters.AuthHomePresenter;
import com.leopin.parkfifty.client.presenters.Presenter;
import com.leopin.parkfifty.client.resources.AppStyles;
import com.leopin.parkfifty.client.resources.AppStyles.AppResources;
import com.leopin.parkfifty.client.resources.AppStyles.Style;
import com.leopin.parkfifty.client.ui.AuthMenu;
import com.leopin.parkfifty.shared.messages.FieldLabels;
import com.leopin.parkfifty.shared.messages.ValidationMessages;

public class AuthHomeViewImpl implements AuthHomeView {
	
	AuthHomePresenter presenter;
	AuthMenu menu;
	FlowPanel wrapper;

	public AuthHomeViewImpl() {
		this.menu = new AuthMenu();
		this.wrapper = new FlowPanel();
		wrapper.add(this.menu);
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = (AuthHomePresenter) presenter;
	}

	
	@Override
	public Widget asWidget() {
		return this.wrapper;
	}

	@Override
	public Style style() {
		return AppStyles.style();
	}
	
	@Override
	public AppResources resources() {
		return AppStyles.resources();
	}

	@Override
	public ValidationMessages validationMessages() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public FieldLabels fieldLabels() {
		return FieldLabels.INSTANCE;
	}
	
	@Override
	public void clickOnManageLocations() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clickOnManageRates() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clickOnListLocations() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize() {
		// Nothing to initialize
		
	}

}
