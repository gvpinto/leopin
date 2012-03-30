package com.leopin.parkfifty.client.events;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasAppSuccessHandlers extends HasHandlers {
	HandlerRegistration addAppSuccessHandlers(AppSuccessHandler handler);
}
