package com.leopin.parkfifty.shared.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ValidationMessages extends Messages {

		public static final ValidationMessages INSTANCE = (ValidationMessages) GWT.create(ValidationMessages.class);

		@Key("com.leopin.contraints.company.name")
		String companyNameInvalid();

		@Key("com.leopin.contraints.email")
		String emailInvalid();

		@Key("com.leopin.contraints.url")
		String urlInvalid();

		@Key("com.leopin.contraints.primary.phone")
		String priPhoneNumInvalid();

		@Key("com.leopin.contraints.secondary.phone")
		String secPhoneNumInvalid();

		@Key("com.leopin.contraints.fax")
		String faxInvalid();

		@Key("com.leopin.contraints.userid")
		String userIdInvalid();

		@Key("com.leopin.contraints.password")
		String passwordInvalid();

		@Key("com.leopin.contraints.title")
		String titleInvalid();

		@Key("com.leopin.contraints.lastname")
		String lastNameInvalid();

		@Key("com.leopin.contraints.firstname")
		String firstNameInvalid();

		@Key("com.leopin.contraints.middleinitial")
		String middleInitialInvalid();

		@Key("com.leopin.contraints.suffix")
		String suffixInvalid();

}
