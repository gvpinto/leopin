package com.leopin.parkfifty.shared.constants;

public enum HTTPHeaders {
	
	CONTENT_TYPE_JSON("Content-type", "application/json;charset=UTF-8");
	
	String key;
	String value;
	
	HTTPHeaders(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public String getValue() {
		return this.value;
	}
}
