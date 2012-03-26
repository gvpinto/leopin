package com.leopin.parkfifty.shared.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ValidationMessages extends Messages {

	public static final ValidationMessages INSTANCE = (ValidationMessages) GWT.create(ValidationMessages.class);
	
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
	
	@Key("com.leopin.contraints.userid.invalid")
	String userIdInvalid();
	
	@Key("com.leopin.contraints.password.invalid")
	String passwordInvalid();
	
	@Key("com.leopin.contraints.title.invalid")
	String titleInvalid();
	
	@Key("com.leopin.contraints.lastname.invalid")
	String lastNameInvalid();
	
	@Key("com.leopin.contraints.firstname.invalid")
	String firstNameInvalid();
	
	@Key("com.leopin.contraints.middleinitial.invalid")
	String middleInitialInvalid();
	
	@Key("com.leopin.contraints.suffix.invalid")
	String suffixInvalid();

	
}
