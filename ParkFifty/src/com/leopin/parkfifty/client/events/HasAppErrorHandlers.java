package com.leopin.parkfifty.client.events;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasAppErrorHandlers extends HasHandlers {
	HandlerRegistration addAppErrorHandlers(AppErrorHandler handler);
}
