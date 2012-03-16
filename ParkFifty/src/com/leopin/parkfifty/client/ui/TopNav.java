package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class TopNav extends Composite implements HasText {

	private static TopNavUiBinder uiBinder = GWT.create(TopNavUiBinder.class);

	interface TopNavUiBinder extends UiBinder<Widget, TopNav> {
	}

	public TopNav() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Anchor anchor;

	public TopNav(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
