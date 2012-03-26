package com.leopin.parkfifty.client.domain;

import com.google.gwt.core.client.JavaScriptObject;

public class ErrorInfo extends JavaScriptObject {
	protected ErrorInfo() {}
	
	public final native String getKey()
	/*-{ return this.key}-*/;
	
	public final native String getDescription()
	/*-{ return this.description}-*/;
	
}
