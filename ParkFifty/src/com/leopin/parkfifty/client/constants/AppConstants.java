package com.leopin.parkfifty.client.constants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface AppConstants extends Constants {
	
	public static AppConstants INSTANCE = (AppConstants) GWT.create(AppConstants.class);

	public int helpTextWidth();
	public int helpTextHeight();
	
}
