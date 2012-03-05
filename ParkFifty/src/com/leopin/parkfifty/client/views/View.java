package com.leopin.parkfifty.client.views;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.presenters.Presenter;
import com.leopin.parkfifty.client.resources.ParkFiftyResources;

public interface View extends IsWidget {
	void setPresenter(Presenter presenter);
	Widget asWidget();
	ParkFiftyResources.Style style();
}
