package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.domain.CompanyUserProxyImpl;
import com.leopin.parkfifty.client.resources.ParkFiftyResources;
import com.leopin.parkfifty.client.resources.ParkFiftyResources.Style;
import com.leopin.parkfifty.shared.domain.CompanyUserProxy;

public class CompanyOwnerWidget extends Composite implements UiWidget {

	private static CompanyOwnerWidgetUiBinder uiBinder = GWT
			.create(CompanyOwnerWidgetUiBinder.class);

	interface CompanyOwnerWidgetUiBinder extends
			UiBinder<Widget, CompanyOwnerWidget> {
	}

	public CompanyOwnerWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		// Size the 2nd column on the grid to avoid the jumping of the table 
		// when the help icon is displayed
		uiGrid.getColumnFormatter().addStyleName(0, this.style().labelCol());
		uiGrid.getColumnFormatter().addStyleName(1, this.style().fieldCol());
	}


	public CompanyOwnerWidget(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Grid uiGrid;
	
	@UiField
	TextBoxCombo uiUsername;
	
	public TextBoxCombo getUiUsername() {
		return uiUsername;
	}
	
	@UiField
	PasswordCombo uiPassword;
	
	public PasswordCombo getUiPassword() {
		return uiPassword;
	}
	
	@UiField
	TextBoxCombo uiTitle;
	
	public TextBoxCombo getUiTitle() {
		return uiTitle;
	}
	
	@UiField
	TextBoxCombo uiFirstName;
	
	public TextBoxCombo getUiFirstName() {
		return uiFirstName;
	}
	
	@UiField
	TextBoxCombo uiMiddleInitial;
	
	public TextBoxCombo getUiMiddleInitial() {
		return uiMiddleInitial;
	}
	
	@UiField
	TextBoxCombo uiLastName;
	
	public TextBoxCombo getUiLastName() {
		return uiLastName;
	}
	
	@UiField
	TextBoxCombo uiSuffix;
	
	public TextBoxCombo getUiSuffix() {
		return uiSuffix;
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
	
	@UiField
	TextBoxCombo uiEmail;
	
	public TextBoxCombo getUiEmail() {
		return uiEmail;
	}
	
	/**
	 * Initialize Focus Handlers for Composites using this widget
	 * @param handler FocusHandler
	 */
	public void initFocusHandlers(FocusHandler handler) {
		uiUsername.addFocusHandler(handler);
		uiPassword.addFocusHandler(handler);
		uiTitle.addFocusHandler(handler);
		uiFirstName.addFocusHandler(handler);
		uiMiddleInitial.addFocusHandler(handler);
		uiLastName.addFocusHandler(handler);
		uiSuffix.addFocusHandler(handler);
		uiPriPhone.addFocusHandler(handler);
		uiSecPhone.addFocusHandler(handler);
		uiFax.addFocusHandler(handler);
		uiEmail.addFocusHandler(handler);
	}
	
	/**
	 * Initialize Blur Handler for Composites using this widget
	 * @param handler BlurHandler
	 */
	public void initBlurHandlers(BlurHandler handler) {
		uiUsername.addBlurHandler(handler);
		uiPassword.addBlurHandler(handler);
		uiTitle.addBlurHandler(handler);
		uiFirstName.addBlurHandler(handler);
		uiMiddleInitial.addBlurHandler(handler);
		uiLastName.addBlurHandler(handler);
		uiSuffix.addBlurHandler(handler);
		uiPriPhone.addBlurHandler(handler);
		uiSecPhone.addBlurHandler(handler);
		uiFax.addBlurHandler(handler);
		uiEmail.addBlurHandler(handler);
	}
	
	public CompanyUserProxy getCompanyUserProxy() {
		CompanyUserProxy companyUserProxy = new CompanyUserProxyImpl();
		companyUserProxy.setUsername(uiUsername.getText());
		companyUserProxy.setPassword(uiPassword.getText());
		companyUserProxy.setTitle(uiTitle.getText());
		companyUserProxy.setFirstName(uiFirstName.getText());
		companyUserProxy.setMiddleInitial(uiMiddleInitial.getText());
		companyUserProxy.setLastName(uiLastName.getText());
		companyUserProxy.setSuffix(uiSuffix.getText());
		companyUserProxy.setPriPhone(uiPriPhone.getText());
		companyUserProxy.setSecPhone(uiSecPhone.getText());
		companyUserProxy.setFax(uiFax.getText());
		companyUserProxy.setEmail(uiEmail.getText());
		
		return companyUserProxy;
	}

	@Override
	public Style style() {
		return ParkFiftyResources.INSTANCE.style();
	}
	
}
