package com.leopin.parkfifty.client.views;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.presenters.AuthHomePresenter;
import com.leopin.parkfifty.client.presenters.Presenter;
import com.leopin.parkfifty.client.resources.AppStyles;
import com.leopin.parkfifty.client.resources.AppStyles.AppResources;
import com.leopin.parkfifty.client.resources.AppStyles.Style;
import com.leopin.parkfifty.shared.messages.ValidationMessages;

public class AuthHomeViewImpl extends Composite implements AuthHomeView {
	
	AuthHomePresenter presenter;

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = (AuthHomePresenter) presenter;
	}

	
	@Override
	public Widget asWidget() {
		return this;
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

}
