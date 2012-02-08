package com.leopin.parkfifty.client.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface AppMessages extends Messages {

	public static final AppMessages INSTANCE = (AppMessages) GWT.create(AppMessages.class);
	
	@Key("com.leopin.contraints.company.name.invalid")
	String companyNameInvalid();
	
	@Key("com.leopin.contraints.email.invalid")
	String emailInvalid();
	
	@Key("com.leopin.contraints.url.invalid")
	String urlInvalid();
	
	@Key("com.leopin.contraints.primary.phone.invalid")
	String priPhoneNumInvalid();
	
	@Key("com.leopin.contraints.secondary.phone.invalid")
	String secPhoneNumInvalid();
	
	@Key("com.leopin.contraints.fax.invalid")
	String faxInvalid();
	
}
