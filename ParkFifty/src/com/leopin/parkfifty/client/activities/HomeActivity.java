package com.leopin.parkfifty.client.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.leopin.parkfifty.client.ClientFactory;
import com.leopin.parkfifty.client.places.CompanyRegistrationPlace;
import com.leopin.parkfifty.client.places.HomePlace;
import com.leopin.parkfifty.client.presenters.HomePresenter;
import com.leopin.parkfifty.client.views.HomeView;
import com.leopin.parkfifty.shared.utils.AppRegExp;
import com.leopin.parkfifty.shared.utils.Utils;

public class HomeActivity extends AbstractActivity implements HomePresenter {
	
	
	private ClientFactory clientFactory;
	private HomeView homeView;
	
	public HomeActivity(HomePlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		homeView = clientFactory.getHomeView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(homeView.asWidget());
		bind();
	}

    private void bind() {
		homeView.setPresenter(this);
	}

	/**
     * Ask user before stopping this activity
     */
    @Override
    public String mayStop() {
//        return "Please hold on. This activity is stopping.";
    	return null;
    }
    
	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public boolean validate(String value, String regex, boolean isRequired) {
		return Utils.validate(value, AppRegExp.COMPANY_NAME, isRequired);
	}

	@Override
	public void next() {
		goTo(new CompanyRegistrationPlace());
	}

	@Override
	public boolean validateName(String value) {
		return Utils.validate(value, AppRegExp.COMPANY_NAME, true);
	}

	@Override
	public boolean validateUrl(String value) {
		return Utils.validate(value, AppRegExp.URL, true);
	}

	@Override
	public boolean validateEmail(String value) {
		return Utils.validate(value, AppRegExp.EMAIL, true);
	}

	@Override
	public boolean validatePriPhone(String value) {
		return Utils.validate(value, AppRegExp.PHONE_NUM, true);
	}

	@Override
	public boolean validateOtherPhone(String value) {
		return Utils.validate(value, AppRegExp.PHONE_NUM, false);
	}


	@Override
	public String stripChars(String value) {
		return Utils.stripChars(value);
	}

	@Override
	public boolean validateSecPhone(String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String formatPhoneNum(String value) {
		return Utils.formatPhoneNum(value);
	}

}
