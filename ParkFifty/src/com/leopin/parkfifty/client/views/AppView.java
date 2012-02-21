package com.leopin.parkfifty.client.views;

import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.leopin.parkfifty.client.presenters.Presenter;

public interface AppView extends IsWidget  {
	void setPresenter(Presenter presenter);
	public SimplePanel getContent();	
	public SimplePanel getHeader();
	public SimplePanel getFooter();
}
