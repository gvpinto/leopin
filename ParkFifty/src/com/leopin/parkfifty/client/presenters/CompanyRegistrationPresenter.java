package com.leopin.parkfifty.client.presenters;

public interface CompanyRegistrationPresenter extends Presenter {
	
	boolean validate(String name, String value);
	void submit();
	void onFocus(String name, String text);
	
}
