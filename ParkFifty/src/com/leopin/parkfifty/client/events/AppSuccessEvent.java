package com.leopin.parkfifty.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class AppSuccessEvent extends GwtEvent<AppSuccessHandler> {

	private static Type<AppSuccessHandler> TYPE = new Type<AppSuccessHandler>();
	private String message;
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	@Override
	public Type<AppSuccessHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AppSuccessHandler handler) {
		handler.onAppSuccessEvent(this);
	}
	
	public static Type<AppSuccessHandler> getType() {
		return TYPE;
	}
	
	static protected void fire(HasAppSuccessHandlers source) {
		source.fireEvent(new AppErrorEvent());
	}

}
