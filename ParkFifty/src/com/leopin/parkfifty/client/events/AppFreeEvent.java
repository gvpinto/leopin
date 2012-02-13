package com.leopin.parkfifty.client.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * When an App Transistions from BUSY to FREE
 * @author Glenn Pinto
 *
 */
public class AppFreeEvent extends GwtEvent<AppFreeHandler> {

	private static Type<AppFreeHandler> TYPE = new Type<AppFreeHandler>();
	
	@Override
	public GwtEvent.Type<AppFreeHandler> getAssociatedType() {
		return TYPE;
	}

	public static GwtEvent.Type<AppFreeHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AppFreeHandler handler) {
		handler.onAppFreeEvent(this);
	}
	
	protected void fire(HasAppFreeHandlers source) {
		source.fireEvent(new AppFreeEvent());
	}

}
