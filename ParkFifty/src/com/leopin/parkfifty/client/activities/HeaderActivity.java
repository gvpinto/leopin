package com.leopin.parkfifty.client.activities;

import static com.leopin.parkfifty.shared.constants.AppURI.ADMIN_LOGIN_CHECK;
import static com.leopin.parkfifty.shared.utils.Validator.validatePassword;
import static com.leopin.parkfifty.shared.utils.Validator.validateUsername;

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
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.leopin.parkfifty.client.ClientFactory;
import com.leopin.parkfifty.client.places.AuthHomePlace;
import com.leopin.parkfifty.client.places.HomePlace;
import com.leopin.parkfifty.client.presenters.HeaderPresenter;
import com.leopin.parkfifty.client.views.HeaderView;
import com.leopin.parkfifty.shared.constants.CompanyUserFields;
import com.leopin.parkfifty.shared.messages.AppMessages;

public class HeaderActivity extends AbstractActivity implements HeaderPresenter {

	EventBus eventBus;
	ClientFactory clientFactory;
	boolean isLoggedIn = false;
	HeaderView headerView;
	
	// Field name the failed the last validation. 
	// Useful to set the focus when the submit or login button is clicked
	String name;

	public HeaderActivity(HomePlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		headerView = this.clientFactory.getHeaderView();
		if ("init".equals(place.getToken())) {

		}
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void bind() {
		this.headerView.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		bind();
		this.eventBus = eventBus;
		panel.setWidget(headerView.asWidget());
		isAuthenticated();
	}

	private void setLoginText(boolean loginSuccessful) {
		headerView.setLoginText(loginSuccessful);
	}

	@Override
	public void isAuthenticated() {

		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET,
				GWT.getHostPageBaseURL() + ADMIN_LOGIN_CHECK);
		// rb.setHeader("Content-type", "application/x-www-form-urlencoded");
		// rb.setHeader("Content-type", "application/x-www-form-urlencoded");
		// String request = "j_username=" + userId + "&j_password=" + password;
		// rb.setRequestData(URL.encode(request));

		rb.setCallback(new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				if (200 == response.getStatusCode()) {
					GWT.log("SUCCESS");
					setLoginText(true);
				} else {
					GWT.log("ERROR: Code: " + response.getStatusCode());
					setLoginText(false);
				}
			}

			@Override
			public void onError(Request request, Throwable exception) {
				GWT.log("ERROR: Message: " + exception.getMessage());
				setLoginText(false);
			}
		});

		try {
			rb.send();
		} catch (RequestException e) {
			setLoginText(false);
			GWT.log("ERROR: Code: " + e.getMessage());
			// TODO Throw and Error. Introduce a Event that can be fired if
			// there is an Error anywhere in the App
		}

	}

	@Override
	public String attemptToLogin(String username, String password) {

		this.name = null;
		// Validate the User ID and Password
		if (validate(CompanyUserFields.UiUsername.getId(), username)
				&& validate(CompanyUserFields.UiPassword.getId(), password)) {

			// Make a call to the Server to attempt to authenticate
			// If Authenticated the application will segue into the Authensticated Home Screen
			login(username, password);
			this.name = null;
	
		}
		
		// Will be null if the login passes
		return name;

	}

	/**
	 * Validate the input before submitting the data to the server The data is
	 * once again validated on the server to ensure its not changed after its
	 * submitted
	 */
	@Override
	public boolean validate(String name, String value) {
		
		boolean pass = true;
		this.name = name;
		
		if (name.matches("uiUsername")) {
			if (!validateUsername(value)) {
				pass = false;
			}
		
		} else if (name.matches("uiPassword")) {
		
			if (!validatePassword(value)) {
				pass = false;
			}
		
			
		}
		
		if (!pass) {
			headerView.showHelp(name);
		} else {
			headerView.removeHelp(name);
		}
		
		return pass;
	}

	private void login(final String username, final String password) {

		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST,
				"/j_spring_security_check");
		rb.setHeader("Content-type", "application/x-www-form-urlencoded");
		String request = "j_username=" + username + "&j_password=" + password;
		rb.setRequestData(URL.encode(request));

		rb.setCallback(new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {
				if (200 == response.getStatusCode()) {
					// Authenticated Successful. Go to the Authenticated Home Pages
					goTo(new AuthHomePlace());
					
				} else if (401 == response.getStatusCode()) {
					// Handle error to be displayed for invalid authentication
					headerView.setErrorMsg(messages().loginFailed());
					
				} else {
					// Handle error to be displayed for invalid authentication
					headerView.setErrorMsg(messages().loginFailed());
					// TODO: Handle error such as system exception
					// uiErrorTxt.setText("Unknown Error - Reponse Code: " +
					// response.getStatusCode() + " - Response Message: " +
					// response.getStatusText());
				}

			}

			@Override
			public void onError(Request request, Throwable e) {
				headerView.setErrorMsg(messages().loginFailed());
			}

		});

		// TODO: Wait Image needs to be displayed
		try {
			rb.send();
		} catch (RequestException e) {
			headerView.setErrorMsg(messages().loginFailed());
		}

	}

	@Override
	public AppMessages messages() {
		return AppMessages.INSTANCE;
	}

}
