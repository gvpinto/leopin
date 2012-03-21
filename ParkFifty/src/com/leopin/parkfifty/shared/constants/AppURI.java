package com.leopin.parkfifty.shared.constants;

public enum AppURI {
	
	ADMIN_LOGIN_CHECK("/admin/logincheck"),
	ADD_COMPANY("/admin/company");
	
	String uri;
	
	AppURI(String uri) {
		this.uri = uri; 
	}
	
	public String getUri() {
		return this.uri;
	}
	
	public String toString() {
		return uri;
	}
}
