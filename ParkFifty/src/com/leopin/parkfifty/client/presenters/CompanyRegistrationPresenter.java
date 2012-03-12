package com.leopin.parkfifty.client.presenters;

import com.leopin.parkfifty.shared.domain.CompanyProxy;
import com.leopin.parkfifty.shared.domain.CompanyUserProxy;

public interface CompanyRegistrationPresenter extends Presenter {
	
	boolean validate(String name, String value);
	void submit(CompanyProxy company, CompanyUserProxy companyUser);
	void onFocus(String name, String text);
	
}
