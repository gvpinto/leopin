package com.leopin.parkfifty.client.presenters;

import com.leopin.parkfifty.client.domain.CompanyProxy;

public interface HomePresenter extends Presenter {
	public boolean validator(String value, String regex, boolean isRequired);
	public boolean validateName(String value);
	public boolean validateUrl(String value);
	public boolean validateEmail(String value);
	public boolean validatePriPhone(String value);
	public boolean validateSecPhone(String value);
	public boolean validateOtherPhone(String value);
	public boolean validate(String name, String value);
	public String stripChars(String value);
	public String formatPhoneNum(String value);
	public void next(CompanyProxy company);
	public void onFocus(String name, String text);
}
