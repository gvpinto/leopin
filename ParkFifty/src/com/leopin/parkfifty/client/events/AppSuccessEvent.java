package com.leopin.parkfifty.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.place.shared.Place;

public class AppSuccessEvent extends GwtEvent<AppSuccessHandler> {

	private static Type<AppSuccessHandler> TYPE = new Type<AppSuccessHandler>();
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
