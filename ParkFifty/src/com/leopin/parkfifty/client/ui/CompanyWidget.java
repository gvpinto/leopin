package com.leopin.parkfifty.client.ui;

import static com.leopin.parkfifty.shared.constants.CompanyFields.UiEmail;
import static com.leopin.parkfifty.shared.constants.CompanyFields.UiFax;
import static com.leopin.parkfifty.shared.constants.CompanyFields.UiName;
import static com.leopin.parkfifty.shared.constants.CompanyFields.UiPriPhone;
import static com.leopin.parkfifty.shared.constants.CompanyFields.UiSecPhone;
import static com.leopin.parkfifty.shared.constants.CompanyFields.UiUrl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.domain.CompanyProxyImpl;
import com.leopin.parkfifty.client.resources.AppStyles;
import com.leopin.parkfifty.client.resources.AppStyles.AppResources;
import com.leopin.parkfifty.client.resources.AppStyles.Style;
import com.leopin.parkfifty.shared.domain.CompanyProxy;
import com.leopin.parkfifty.shared.messages.FieldLabels;
import com.leopin.parkfifty.shared.messages.ValidationMessages;

public class CompanyWidget extends Composite implements UiWidget, UiFormWidget {

	private static final String String = null;

	private static CompanyWidgetUiBinder uiBinder = GWT
			.create(CompanyWidgetUiBinder.class);
	
	interface CompanyWidgetUiBinder extends UiBinder<Widget, CompanyWidget> {
	}

	
	public CompanyWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		
		// Set the Help Text in advance from the AppMessages properties file
		uiName.setHelpText(validationMessages().companyNameInvalid());
		uiUrl.setHelpText(validationMessages().urlInvalid());
		uiEmail.setHelpText(validationMessages().emailInvalid());
		uiPriPhone.setHelpText(validationMessages().priPhoneNumInvalid());
		uiSecPhone.setHelpText(validationMessages().secPhoneNumInvalid());
		uiFax.setHelpText(validationMessages().faxInvalid());
		
		// Set the Labels of the fields
		uiName.setLabel(fieldLabels().companyName(), true);
		uiUrl.setLabel(fieldLabels().websiteUrl(), true);
		uiEmail.setLabel(fieldLabels().emailAddress(), true);
		uiPriPhone.setLabel(fieldLabels().phoneNumber(), true);
		uiSecPhone.setLabel(fieldLabels().secondaryPhoneNumber(), false);
		uiFax.setLabel(fieldLabels().fax(), false);
		
	}
	
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
	
	

	@Override
	public void initHandlers(EventHandler handler) {

		initFocusHandlers ((FocusHandler) handler);
		initBlurHandlers((BlurHandler)handler);

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
	
	public Widget getWidget(String name) {
		Widget widget = null;
		
		if (name.matches(UiName.getId())) {
			widget = this.getUiName();
		} else if (name.matches(UiUrl.getId())) {
			widget = this.getUiUrl();
		} else if (name.matches(UiEmail.getId())) {
			widget = this.getUiEmail();
		} else if (name.matches(UiPriPhone.getId())) {
			widget = this.getUiPriPhone();
		} else if (name.matches(UiSecPhone.getId())) {
			widget = this.getUiSecPhone();
		} else if (name.matches(UiFax.getId())) {
			widget = this.getUiFax();
		}
		return widget;
	}

	
	/**
	 * Clear all the widgets
	 */
	public void clear() {
		getUiName().setText("");
		getUiEmail().setText("");
		getUiFax().setText("");
		getUiPriPhone().setText("");
		getUiSecPhone().setText("");
		getUiUrl().setText("");
	}
	
	@Override
	public Style style() {
		return AppStyles.style();
	}
	
	@Override
	public AppResources resources() {
		return AppStyles.resources();
	}
	
	@Override
	public ValidationMessages validationMessages() {
		return ValidationMessages.INSTANCE;
	}

	@Override
	public FieldLabels fieldLabels() {
		return FieldLabels.INSTANCE;
	}

	@Override
	public void hideHelpTexts() {
		uiName.hideHelpText();
		uiUrl.hideHelpText();
		uiEmail.hideHelpText();
		uiPriPhone.hideHelpText();
		uiSecPhone.hideHelpText();
		uiFax.hideHelpText();
	}
	
}
