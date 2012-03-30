package com.leopin.parkfifty.shared.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface AppMessages extends Messages {

	public static final AppMessages INSTANCE = (AppMessages) GWT.create(AppMessages.class);
	
	@Key("error.app.admin.user.exists")
	String adminUserExists(String username);
	
	@Key("success.app.admin.add.company")
	String addCompanySuccessful(String companyName);
	
}
