package com.leopin.parkfifty.client.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is triggered when an App transitions from FREE to BUSY
 * @author Glenn Pinto
 *
 */
public class AppBusyEvent extends GwtEvent<AppBusyHandler> {

	private static Type<AppBusyHandler> TYPE = new Type<AppBusyHandler>();

	/**
	 * Gets the event type associated with load events.
	 * 
	 * @return the handler type
	 */
	public static GwtEvent.Type<AppBusyHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AppBusyHandler handler) {
		handler.onAppBusyEvent(this);
	}
	
	protected void fire(HasAppBusyHandlers source) {
		source.fireEvent(new AppBusyEvent());
	}
	
	@Override
	public GwtEvent.Type<AppBusyHandler> getAssociatedType() {
		return TYPE;
	}

}
