package com.leopin.parkfifty.client.activities;

import static com.leopin.parkfifty.shared.utils.Validator.*;
import static com.leopin.parkfifty.shared.utils.Utils.*;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
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
		
	}

	@Override
	public void goTo(Place place) {
		// TODO Auto-generated method stub

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
	public void onFocus(String name, String text) {
		if (name.matches("uiPriPhone|uiSecPhone|uiFax|uiUserPriPhone|uiUserSecPhone|uiUserFax")) {
			companyRegistrationView.setUiText(name, stripChars(text));
		}
	}


	@Override
	public void submit(CompanyProxy company, CompanyUserProxy companyUser) {
		
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
			
		} else {
			companyRegistrationView.setFocus(name);
		}
	
	}

}