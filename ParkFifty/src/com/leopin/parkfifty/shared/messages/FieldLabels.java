package com.leopin.parkfifty.shared.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface FieldLabels extends Messages {
	
	public static final FieldLabels INSTANCE = (FieldLabels) GWT
			.create(FieldLabels.class);

	// FIELD LABLES
	@Key("com.leopin.field.label.company.name")
	String companyName();
	
	@Key("com.leopin.field.label.website.url")
	String websiteUrl();
	
	@Key("com.leopin.field.label.email.address")
	String emailAddress();
	
	@Key("com.leopin.field.label.phone.number")
	String phoneNumber();
	
	@Key("com.leopin.field.label.secondary.phone.number")
	String secondaryPhoneNumber();
	
	@Key("com.leopin.field.label.fax")
	String fax();
	
	@Key("com.leopin.field.label.user.id")
	String userId();

	@Key("com.leopin.field.label.password")
	String password();
	
	@Key("com.leopin.field.label.title")
	String title();
	
	@Key("com.leopin.field.label.first.name")
	String firstName();
	
	@Key("com.leopin.field.label.middle.initial")
	String middleInitial();
	
	@Key("com.leopin.field.label.last.name")
	String lastName();
	
	@Key("com.leopin.field.label.suffix")
	String suffix();
	
	// TITLES
	@Key("com.leopin.field.title.company.registration")
	String titleCompanyRegistration();
	
	@Key("com.leopin.field.title.company.registration.continued")
	String titleCompanyRegistrationContinued();
	
}
