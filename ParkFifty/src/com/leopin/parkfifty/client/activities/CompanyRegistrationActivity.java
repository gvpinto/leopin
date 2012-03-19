package com.leopin.parkfifty.client.activities;

import static com.leopin.parkfifty.shared.utils.Utils.formatPhoneNum;
import static com.leopin.parkfifty.shared.utils.Utils.stripChars;
import static com.leopin.parkfifty.shared.utils.Validator.validateEmail;
import static com.leopin.parkfifty.shared.utils.Validator.validateMiddleInitial;
import static com.leopin.parkfifty.shared.utils.Validator.validateName;
import static com.leopin.parkfifty.shared.utils.Validator.validateOtherPhone;
import static com.leopin.parkfifty.shared.utils.Validator.validatePassword;
import static com.leopin.parkfifty.shared.utils.Validator.validatePriPhone;
import static com.leopin.parkfifty.shared.utils.Validator.validateSuffix;
import static com.leopin.parkfifty.shared.utils.Validator.validateTitle;
import static com.leopin.parkfifty.shared.utils.Validator.validateUrl;
import static com.leopin.parkfifty.shared.utils.Validator.validateUserName;
import static com.leopin.parkfifty.shared.utils.Validator.validateUsername;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.leopin.parkfifty.client.ClientFactory;
import com.leopin.parkfifty.client.places.CompanyRegistrationPlace;
import com.leopin.parkfifty.client.presenters.CompanyRegistrationPresenter;
import com.leopin.parkfifty.client.views.CompanyRegistrationView;
import com.leopin.parkfifty.shared.domain.CompanyProxy;
import com.leopin.parkfifty.shared.domain.CompanyUserProxy;

public class CompanyRegistrationActivity extends AbstractActivity implements
		CompanyRegistrationPresenter {

	CompanyRegistrationView companyRegistrationView;
	ClientFactory clientFactory;
	EventBus eventBus;
	
	// This field is used to determine the first widget the failed validation
	// do that the focus can be set to that field
	String name;
	
	public CompanyRegistrationActivity(CompanyRegistrationPlace place,
			ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.companyRegistrationView = clientFactory.getCompanyRegistrationView();
		this.companyRegistrationView.setCompany(place.getCompanyProxy());
		
	}

	@Override
	public void goTo(Place place) {

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.eventBus = eventBus;
		panel.setWidget(this.companyRegistrationView);
		bind();
	}

	/**
	 * Bind the presenter and events
	 */
	public void bind() {
		this.companyRegistrationView.setPresenter(this);
	}


	/**
	 * Validate the input before submitting the data to the server
	 * The data is once again validated on the server to ensure its not
	 * changed after its submitted
	 */
	@Override
	public boolean validate(String name, String value) {
		
		boolean pass = true;
		boolean valid = false;
		String tempPhone = null;
		this.name = name;
		
		if (name.matches("uiName")) {
			
			if (!validateName(value)) {
				pass = false;
			}
			
		} else if (name.matches("uiUrl")) {
			
			if (!validateUrl(value)) {
				pass = false;
			}
			
		} else if (name.matches("uiEmail|uiUserEmail")) {
			
			if (!validateEmail(value)) {
				pass = false;
			}
			
		} else if (name.matches("uiPriPhone|uiUserPriPhone")) {
			
			valid = true;
//			homeView.setUiText(name, this.stripChars(value));
			tempPhone = stripChars(value);
			if (!validatePriPhone(stripChars(tempPhone))) {
				valid = false;
				pass = false;
			}

			
		} else if (name.matches("uiFax|uiSecPhone|uiUserFax|uiUserSecPhone")) {
			
			valid = true;
			tempPhone = stripChars(value);
			
			if (!validateOtherPhone(tempPhone)) {
				valid = false;
				pass = false;
			}

		} else if (name.matches("uiUserUsername")) {
			if (!validateUsername(value)) {
				pass = false;
			}
			
		} else if (name.matches("uiUserPasssword")) {
			
			if (!validatePassword(value)) {
				pass = false;
			}
			
		} else if (name.matches("uiUserTitle")) {
			
			if (!validateTitle(value)) {
				pass = false;
			}
			
		} else if (name.matches("uiUserFirstName|uiUserLastName")) {
			
			if (!validateUserName(value)) {
				pass = false;
			}
			
		} else if (name.matches("uiUserMiddleInitial")) {
			
			if (!validateMiddleInitial(value)) {
				pass = false;
			}
			
		} else if (name.matches("uiUserSuffix")) {
			
			if (!validateSuffix(value)) {
				pass = false;
			}
			
		}
		
		// Format the Phone Number if he entered data is valid
		if (valid) {
			companyRegistrationView.setUiText(name, formatPhoneNum(value));
		}
		
		if (!pass) {
			companyRegistrationView.showHelp(name);
		} else {
			companyRegistrationView.removeHelp(name);
		}
		
		return pass;
	}

	@Override
	public void onFocusSetValue(String name, String text) {
		if (name.matches("uiPriPhone|uiSecPhone|uiFax|uiUserPriPhone|uiUserSecPhone|uiUserFax")) {
			companyRegistrationView.setUiText(name, stripChars(text));
		}
	}


	@Override
	public String submit(CompanyProxy company, CompanyUserProxy companyUser) {
		
		name = null;
		
		if (validate("uiName", company.getName())
			&& validate("uiUrl", company.getUrl())
			&& validate("uiEmail", company.getEmail())
			&& validate("uiPriPhone", company.getPriPhone())
			&& validate("uiSecPhone", company.getSecPhone())
			&& validate("uiFax", company.getFax())
			&& validate("uiUserUsername", companyUser.getUsername())
			&& validate("uiUserPassword", companyUser.getPassword())
			&& validate("uiUserTitle", companyUser.getTitle())
			&& validate("uiUserFirstName", companyUser.getFirstName())
			&& validate("uiUserMiddleInitial", companyUser.getMiddleInitial())
			&& validate("uiUserLastName", companyUser.getLastName())
			&& validate("uiUserSuffix", companyUser.getSuffix())
			&& validate("uiUserPriPhone", companyUser.getPriPhone())
			&& validate("uiUserSecPhone", companyUser.getSecPhone())
			&& validate("uiUserFax", companyUser.getFax())) {
			
			// Submit the Data to the Server and save it
			// TODO Submit Data
			String jsonString = createJson(company, companyUser);
			GWT.log(jsonString);
			registerCompany(jsonString);
		}
		
		return name;
	
	}

	private void registerCompany(String jsonString) {
		
		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST,
				GWT.getHostPageBaseURL() + "/admin/company");
		
//		rb.setHeader("Content-type", "application/x-www-form-urlencoded");
		rb.setHeader("Content-type", "application/json;charset=UTF-8");
		rb.setRequestData(jsonString);
		
		rb.setCallback(new RequestCallback() {
			
			@Override
			public void onResponseReceived(Request request, Response response) {
				if (200 == response.getStatusCode()) {
					GWT.log("Success");
					// TODO Display companyRegistrationView.registrationSucessful();
				} else {
					GWT.log("Error. Code: " + response.getStatusCode());
					// TODO Display a Popup with with Error Information
				}
				
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
				GWT.log("Error. Code: " + exception.getMessage());
				// TODO Display a Popup with with Error Information				
			}
		});
		
		try {
			rb.send();
		} catch (RequestException e) {
			GWT.log("Error. Code: " + e.getMessage());
			// TODO Display a Popup with with Error Information							
		}
		
	}

	private String createJson(CompanyProxy company,
			CompanyUserProxy companyUser) {
		
		JSONObject jsonParent = new JSONObject();
		
		// Set the Company Info
		JSONObject jsonCompany = new JSONObject();
		jsonCompany.put("id", JSONNull.getInstance());
		jsonCompany.put("name", new JSONString(company.getName()));
		jsonCompany.put("url", new JSONString(company.getUrl()));
		jsonCompany.put("email", new JSONString(company.getEmail()));
		jsonCompany.put("priPhone", new JSONString(company.getPriPhone()));
		jsonCompany.put("secPhone", new JSONString(company.getSecPhone()));
		jsonCompany.put("fax", new JSONString(company.getFax()));
		jsonCompany.put("updateUid", new JSONString(companyUser.getUsername()));
		
		// Set the Company in the Parent JSON
		jsonParent.put("company", jsonCompany);
		
		// Set Company User Info
		JSONObject jsonCompanyUser = new JSONObject();
		jsonCompanyUser.put("id", JSONNull.getInstance());
		jsonCompanyUser.put("username", new JSONString(companyUser.getUsername()));
		jsonCompanyUser.put("password", new JSONString(companyUser.getPassword()));
		jsonCompanyUser.put("title", new JSONString(companyUser.getTitle()));
		jsonCompanyUser.put("firstName", new JSONString(companyUser.getFirstName()));
		jsonCompanyUser.put("middleInitial", new JSONString(companyUser.getMiddleInitial()));
		jsonCompanyUser.put("lastName", new JSONString(companyUser.getLastName()));
		jsonCompanyUser.put("suffix", new JSONString(companyUser.getSuffix()));
		jsonCompanyUser.put("email", new JSONString(companyUser.getEmail()));
		jsonCompanyUser.put("priPhone", new JSONString(companyUser.getPriPhone()));
		jsonCompanyUser.put("secPhone", new JSONString(companyUser.getSecPhone()));
		jsonCompanyUser.put("fax", new JSONString(companyUser.getFax()));
		jsonCompanyUser.put("updateUid", new JSONString(companyUser.getUsername()));
		
		// Set the CompanyUser in the parent JSON
		jsonParent.put("companyUser", jsonCompanyUser);
		
		return jsonParent.toString();
		
	}

}