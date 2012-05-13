package com.leopin.parkfifty.shared.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface FieldLabels extends Messages {
	
	public static final FieldLabels INSTANCE = (FieldLabels) GWT
			.create(FieldLabels.class);

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
	
}
