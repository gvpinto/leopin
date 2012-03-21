package com.leopin.parkfifty.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface AppErrorHandler extends EventHandler {
	public void onAppErrorEvent(AppErrorEvent event);
}
