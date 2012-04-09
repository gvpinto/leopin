package com.leopin.parkfifty.client.activities;

import static com.leopin.parkfifty.shared.constants.AppURI.ADMIN_LOGIN_CHECK;

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

public class HeaderActivity extends AbstractActivity implements HeaderPresenter {

	EventBus eventBus;
	ClientFactory clientFactory;
	boolean isLoggedIn = false;
	HeaderView headerView;

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
	}

	@Override
	public boolean isAuthenticated() {

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
			// TODO Throw and Error. Introduce a Event that can be fired if
			// there is an Error anywhere in the App
		}
		return isLoggedIn;

	}

	@Override
	public void attemptToLogin(String username, String password) {

		// TODO: Validate the User ID and Password

		// TODO: Make a call to the Server to attempt to authenticate

		// TODO: If successful the segue into Authenticated Home View

	}

	private boolean login(final String username, final String password) {

		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST,
				"/j_spring_security_check");
		rb.setHeader("Content-type", "application/x-www-form-urlencoded");
		String request = "j_username=" + username + "&j_password=" + password;
		rb.setRequestData(URL.encode(request));

		rb.setCallback(new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {
				if (200 == response.getStatusCode()) {
					// TODO: 
					goto(new AuthHomePlace());
				} else if (401 == response.getStatusCode()) {
					// TODO: Handle error to be displayed for invalid
					// authentication
				} else {
					// TODO: Handle error such as system exception
					// uiErrorTxt.setText("Unknown Error - Reponse Code: " +
					// response.getStatusCode() + " - Response Message: " +
					// response.getStatusText());
				}

			}

			@Override
			public void onError(Request request, Throwable e) {
				// TODO: Handle error such as system exceptionw
			}
			
		});

		try {
			rb.send();
		} catch (RequestException e) {
			// TODO: Handle error such as system exception
		}

	}

}
