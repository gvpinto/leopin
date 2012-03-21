package com.leopin.parkfifty.shared.constants;

public enum NavigationButtons {

	UiContinue("uiContinue"),
	UiSubmit("uiSubmit"),
	UiCancel("uiCancel");

	String id;
	String labelKey;

	
	NavigationButtons(String id) {
		this(id, "");
	}
	NavigationButtons(String id, String labelKey) {
		this.id = id;
		this.labelKey = labelKey;
	}

	public String getId() {
		return id;
	}
	
	public String getLabelKey() {
		return labelKey;
	}
	
	public String toString() {
		return id;
	}
}
