package com.leopin.parkfifty.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.place.shared.Place;

public class AppErrorEvent extends GwtEvent<AppErrorHandler> {

	private static Type<AppErrorHandler> TYPE = new Type<AppErrorHandler>();
	private String message;
	private Place place;
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setPlace(Place place) {
		this.place = place;
	}
	
	public Place getPlace() {
		return place;
	}
	
	@Override
	public Type<AppErrorHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AppErrorHandler handler) {
		handler.onAppErrorEvent(this);
	}
	
	public static Type<AppErrorHandler> getType() {
		return TYPE;
	}
	
	static protected void fire(HasAppErrorHandlers source) {
		source.fireEvent(new AppErrorEvent());
	}
}
