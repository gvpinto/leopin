package com.leopin.parkfifty.shared.domain;

public enum ValueType {
	AMT("$"), PCT("%");
	
	String valueType;
	
	private ValueType(String valueType) {
		this.valueType = valueType;
	};
	
	public String getValueType() {
		return valueType;
	}
}
