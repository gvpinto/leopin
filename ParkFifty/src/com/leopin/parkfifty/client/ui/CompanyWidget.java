package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.shared.utils.Utils;

public class CompanyWidget extends Composite implements KeyPressHandler {

	private static CompanyWidgetUiBinder uiBinder = GWT
			.create(CompanyWidgetUiBinder.class);

	interface CompanyWidgetUiBinder extends UiBinder<Widget, CompanyWidget> {
	}

	
	public CompanyWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	// Had to rename the button field as continue is a key fiels
	@UiField
	Button uiContinue;
	
	@UiField
	TextBox uiName;
	
	@UiField
	TextBox uiUrl;
	
	@UiField
	TextBox uiEmail;
	
	@UiField
	TextBox uiPriPhone;
	
	@UiField
	TextBox uiSecPhone;
	
	@UiField
	TextBox uiFax;

	@UiHandler("uiContinue")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

	@Override
	public void onKeyPress(KeyPressEvent event) {
		if (event.getUnicodeCharCode() == KeyCodes.KEY_ENTER)
			Window.alert("Enter Key Pressed");
	}
	
	@UiHandler("uiPriPhone")
	public void formatPhone(BlurEvent event) {
		
		NumberFormat.getFormat("(###) ###-###0").format(Utils.scrubPhoneNum(uiPriPhone)); 
		
	}

}
