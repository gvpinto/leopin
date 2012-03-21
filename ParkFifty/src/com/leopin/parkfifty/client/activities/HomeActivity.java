package com.leopin.parkfifty.client.activities;

import static com.leopin.parkfifty.shared.constants.AppURI.*;
import static com.leopin.parkfifty.shared.constants.CompanyFields.*;
import static com.leopin.parkfifty.shared.utils.Validator.*;
import static com.leopin.parkfifty.shared.utils.Utils.*;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.leopin.parkfifty.client.ClientFactory;
import com.leopin.parkfifty.client.places.CompanyRegistrationPlace;
import com.leopin.parkfifty.client.places.HomePlace;
import com.leopin.parkfifty.client.presenters.HomePresenter;
import com.leopin.parkfifty.client.views.HomeView;
import com.leopin.parkfifty.shared.domain.CompanyProxy;

public class HomeActivity extends AbstractActivity implements HomePresenter {
	
	
	private ClientFactory clientFactory;
	private HomeView homeView;
	private boolean isLoggedIn = false;
	
	// The first widget that failed validated when the Continue or Next button is clicked
	// This will help set the focus on that widget
	private String name;
	
	public HomeActivity(HomePlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		homeView = clientFactory.getHomeView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(homeView.asWidget());
		this.homeView.setFocus(UiName.getId());
		bind();
	}

	
    public void bind() {
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
	public boolean validate(String name, String text) {
		
		boolean pass = true;
		boolean valid = false;
		String tempPhone = null;
		this.name = name;
		
		if (name.matches(UiName.getId())) {
			
			if (!validateName(text)) {
				pass = false;
			}
			
		} else if (name.matches(UiUrl.getId())) {
			
			if (!validateUrl(text)) {
				pass = false;
			}
			
		} else if (name.matches(UiEmail.getId())) {
			
			if (!validateEmail(text)) {
				pass = false;
			}
			
		} else if (name.matches(UiPriPhone.getId())) {
			
			valid = true;
//			homeView.setUiText(name, this.stripChars(text));
			tempPhone = stripChars(text);
			if (!validatePriPhone(stripChars(tempPhone))) {
				valid = false;
				pass = false;
			}

			
		} else if (name.matches(UiFax.getId() + "|" + UiSecPhone.getId())) {
			
			valid = true;
			tempPhone = stripChars(text);
			
			if (!validateOtherPhone(tempPhone)) {
				valid = false;
				pass = false;
			}

			
		}
		
		// Format the Phone Number if he entered data is valid
		if (valid) {
			homeView.setUiText(name, formatPhoneNum(text));
		}
		
		if (!pass) {
			homeView.showHelp(name);
		} else {
			homeView.removeHelp(name);
		}
		
		return pass;
	}

	@Override
	public void onFocusSetValue(String name, String text) {
		if (name.matches(UiPriPhone.getId() + "|" + UiSecPhone.getId() + "|" + UiFax.getId())) {
			homeView.setUiText(name, stripChars(text));
		}
	}


	/**
	 * When the user clicks the Continue button
	 */
	@Override
	public String next(CompanyProxy company) {
		
		name = null;
		
		if (validate(UiName.getId(), company.getName()) 
			&& validate(UiUrl.getId(), company.getUrl())
			&& validate(UiEmail.getId(), company.getEmail())
			&& validate(UiPriPhone.getId(), company.getPriPhone())
			&& validate(UiSecPhone.getId(), company.getSecPhone())
			&& validate(UiFax.getId(), company.getFax())) {
			
			CompanyRegistrationPlace companyRegistrationPlace = new CompanyRegistrationPlace("register");
			companyRegistrationPlace.setCompanyProxy(company);
			
			goTo(companyRegistrationPlace);
			
		}
		
		return name;
	}

	@Override
	public boolean isAuthenticated() {
		
		RequestBuilder rb = new RequestBuilder(
				RequestBuilder.GET,
				GWT.getHostPageBaseURL() + ADMIN_LOGIN_CHECK);
//		rb.setHeader("Content-type", "application/x-www-form-urlencoded");
//		rb.setHeader("Content-type", "application/x-www-form-urlencoded");
//		String request = "j_username=" + userId + "&j_password=" + password;
//		rb.setRequestData(URL.encode(request));

		rb.setCallback(new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				if (200 == response.getStatusCode()) {
					GWT.log("SUCCESS");
					isLoggedIn = true;
				} else {
					GWT.log("ERROR: Code: " + response.getStatusCode());
					isLoggedIn = false;
				}
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
				GWT.log("ERROR: Message: " + exception.getMessage());
				isLoggedIn = false;
			}
		});
	
		try {
			rb.send();
		} catch (RequestException e) {
			isLoggedIn = false;
			GWT.log("ERROR: Code: " + e.getMessage());
			// TODO Throw and Error. Introduce a Event that can be fired if there is an Error anywhere in the App
		}
		return isLoggedIn;
		
	}

}
