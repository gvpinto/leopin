package com.leopin.parkfifty.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.messages.AppMessages;
import com.leopin.parkfifty.client.resources.ParkFiftyResources;
import com.leopin.parkfifty.shared.utils.AppRegExp;
import com.leopin.parkfifty.shared.utils.Utils;

public class CompanyWidget extends Composite  {

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
		
		// Set the Help Text in advance from the AppMessages properties file
		uiName.setHelpText(AppMessages.INSTANCE.companyNameInvalid());
		uiUrl.setHelpText(AppMessages.INSTANCE.urlInvalid());
		uiEmail.setHelpText(AppMessages.INSTANCE.emailInvalid());
		uiPriPhone.setHelpText(AppMessages.INSTANCE.priPhoneNumInvalid());
		uiSecPhone.setHelpText(AppMessages.INSTANCE.secPhoneNumInvalid());
		uiFax.setHelpText(AppMessages.INSTANCE.faxInvalid());
		
		// Size the 2nd column on the grid to avoid the jumping of the table 
		// when the help icon is displayed
		uiGrid.getColumnFormatter().setWidth(1, "250px");
		
	}
	
	@UiField
	Grid uiGrid;
	
	@UiField
	Button uiContinue;
	
	@UiField
	TextBoxCombo uiName;
	
	@UiField
	TextBoxCombo uiUrl;
	
	@UiField
	TextBoxCombo uiEmail;
	
	@UiField
	TextBoxCombo uiPriPhone;
	
	@UiField
	TextBoxCombo uiSecPhone;
	
	@UiField
	TextBoxCombo uiFax;

	@UiHandler("uiContinue")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}
	
	/**
	 * Initialize Focus Handlers for Composites using this widget
	 * @param handler FocusHandler
	 */
	public void initFocusHandlers(FocusHandler handler) {
		uiName.addFocusHandler(handler);
		uiUrl.addFocusHandler(handler);
		uiEmail.addFocusHandler(handler);
		uiPriPhone.addFocusHandler(handler);
		uiSecPhone.addFocusHandler(handler);
		uiFax.addFocusHandler(handler);
	}
	
	/**
	 * Initialize Blur Handler for Composites using this widget
	 * @param handler BlurHandler
	 */
	public void initBlurHandlers(BlurHandler handler) {
		uiName.addBlurHandler(handler);
		uiUrl.addBlurHandler(handler);
		uiEmail.addBlurHandler(handler);
		uiPriPhone.addBlurHandler(handler);
		uiSecPhone.addBlurHandler(handler);
		uiFax.addBlurHandler(handler);
	}

	
}
