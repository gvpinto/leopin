package com.leopin.parkfifty.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.resources.ParkFiftyResources;
import com.leopin.parkfifty.shared.utils.AppRegExp;
import com.leopin.parkfifty.shared.utils.Utils;

public class CompanyWidget extends Composite implements KeyPressHandler {

	private static final String String = null;

	private static CompanyWidgetUiBinder uiBinder = GWT
			.create(CompanyWidgetUiBinder.class);

	private static Map regex = new HashMap();
	static {
		regex.put("uiName", AppRegExp.COMPANY_NAME);
	}
	
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
	
	
	@UiHandler(value={"uiName", "uiUrl"})
	public void clearValidation(FocusEvent event) {
		TextBox textBox = (TextBox) event.getSource();
		textBox.removeStyleName(ParkFiftyResources.INSTANCE.style().validateError());
	}
	
	@UiHandler(value={"uiName", "uiUrl", "uiPriPhone", "uiFax", "uiSecPhone"})
	public void formatPhone(BlurEvent event) {
	
		// Validation
		TextBox textBox = (TextBox) event.getSource();
		if (textBox.getName().matches("uiName")) {
			if (!Utils.validate(textBox.getText(), AppRegExp.COMPANY_NAME, true)) {
				uiName.addStyleName(errorStyle());
			}
		} else if(textBox.getName().matches("uiUrl")) {
			if (!Utils.validate(textBox.getText(), AppRegExp.URL, true)) {
				uiUrl.addStyleName(errorStyle());
			}
		}
		
		
		// Phone Number formatting
		if (textBox.getName().matches("uiPriPhone|uiFax|uiSecPhone")) {
			textBox.setText(Utils.formatPhoneNum(textBox.getText()));
		}
		
	}
	
	private String errorStyle() {		
		return ParkFiftyResources.INSTANCE.style().validateError();
	}

//	private boolean validate(String value) {
//		Utils.validate(value, regex);
//	}
	
}
