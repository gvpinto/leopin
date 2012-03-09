package com.leopin.parkfifty.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.domain.CompanyProxyImpl;
import com.leopin.parkfifty.client.messages.AppMessages;
import com.leopin.parkfifty.client.resources.ParkFiftyResources;
import com.leopin.parkfifty.client.resources.ParkFiftyResources.Style;
import com.leopin.parkfifty.shared.domain.CompanyProxy;
import com.leopin.parkfifty.shared.utils.AppRegExp;

public class CompanyWidget extends Composite implements UiWidget {

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
		uiGrid.getColumnFormatter().addStyleName(0, this.style().labelCol());
		uiGrid.getColumnFormatter().addStyleName(1, this.style().fieldCol());
		
	}
	
	@UiField
	Grid uiGrid;
	
	@UiField
	TextBoxCombo uiName;
	
	public TextBoxCombo getUiName() {
		return uiName;
	}
	
	@UiField
	TextBoxCombo uiUrl;
	
	public TextBoxCombo getUiUrl() {
		return uiUrl;
	}
	
	@UiField
	TextBoxCombo uiEmail;
	
	public TextBoxCombo getUiEmail() {
		return uiEmail;
	}
	
	@UiField
	TextBoxCombo uiPriPhone;
	
	public TextBoxCombo getUiPriPhone() {
		return uiPriPhone;
	}
	
	@UiField
	TextBoxCombo uiSecPhone;
	
	public TextBoxCombo getUiSecPhone() {
		return uiSecPhone;
	}
	
	@UiField
	TextBoxCombo uiFax;
	
	public TextBoxCombo getUiFax() {
		return uiFax;
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
	
	public CompanyProxy getCompany() {
		CompanyProxy company = new CompanyProxyImpl();
		company.setName(uiName.getText());
		company.setUrl(uiUrl.getText());
		company.setEmail(uiEmail.getText());
		company.setPriPhone(uiPriPhone.getText());
		company.setSecPhone(uiSecPhone.getText());
		company.setFax(uiFax.getText());
		return company;
	}

	@Override
	public Style style() {
		return ParkFiftyResources.INSTANCE.style();
	}
	
}
