package com.leopin.parkfifty.client.presenters;

import com.leopin.parkfifty.client.domain.CompanyProxy;

public interface HomePresenter extends Presenter {
	public boolean validate(String value, String regex, boolean isRequired);
	boolean validateName(String value);
	boolean validateUrl(String value);
	boolean validateEmail(String value);
	boolean validatePriPhone(String value);
	boolean validateSecPhone(String value);
	boolean validateOtherPhone(String value);
	String stripChars(String value);
	String formatPhoneNum(String value);
	public void next(CompanyProxy company);
}
