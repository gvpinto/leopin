package com.leopin.parkfifty.client.activities;

import static com.leopin.parkfifty.shared.utils.Validator.*;
import static com.leopin.parkfifty.shared.utils.Utils.*;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
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
		
		if (name.matches("uiName")) {
			
			if (!validateName(text)) {
				pass = false;
			}
			
		} else if (name.matches("uiUrl")) {
			
			if (!validateUrl(text)) {
				pass = false;
			}
			
		} else if (name.matches("uiEmail")) {
			
			if (!validateEmail(text)) {
				pass = false;
			}
			
		} else if (name.matches("uiPriPhone")) {
			
			valid = true;
//			homeView.setUiText(name, this.stripChars(text));
			tempPhone = stripChars(text);
			if (!validatePriPhone(stripChars(tempPhone))) {
				valid = false;
				pass = false;
			}

			
		} else if (name.matches("uiFax|uiSecPhone")) {
			
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
	public void onFocus(String name, String text) {
		if (name.matches("uiPriPhone|uiSecPhone|uiFax")) {
			homeView.setUiText(name, stripChars(text));
		}
	}


	/**
	 * When the user clicks the Continue button
	 */
	@Override
	public void next(CompanyProxy company) {
		
		name = null;
		
		if (validate("uiName", company.getName()) 
			&& validate("uiUrl", company.getUrl())
			&& validate("uiEmail", company.getEmail())
			&& validate("uiPriPhone", company.getPriPhone())
			&& validate("uiSecPhone", company.getSecPhone())
			&& validate("uiFax", company.getFax())) {
			
			goTo(new CompanyRegistrationPlace(company));
			
		} else {
			homeView.setFocus(name);
		}
		
	}

}
