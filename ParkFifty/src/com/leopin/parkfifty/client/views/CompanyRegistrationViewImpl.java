package com.leopin.parkfifty.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.presenters.CompanyRegistrationPresenter;
import com.leopin.parkfifty.client.presenters.Presenter;
import com.leopin.parkfifty.client.resources.ParkFiftyResources;
import com.leopin.parkfifty.client.resources.ParkFiftyResources.Style;

public class CompanyRegistrationViewImpl extends Composite implements CompanyRegistrationView {

	CompanyRegistrationPresenter presenter;
	
	private static CompanyRegistrationViewImplUiBinder uiBinder = GWT
			.create(CompanyRegistrationViewImplUiBinder.class);

	interface CompanyRegistrationViewImplUiBinder extends
			UiBinder<Widget, CompanyRegistrationViewImpl> {
	}

	public CompanyRegistrationViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public CompanyRegistrationViewImpl(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = (CompanyRegistrationPresenter) presenter;
	}
	
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public Style style() {
		return ParkFiftyResources.INSTANCE.style();
	}

}
