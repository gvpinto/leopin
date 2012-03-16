package com.leopin.parkfifty.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.presenters.CompanyRegistrationPresenter;
import com.leopin.parkfifty.client.presenters.Presenter;
import com.leopin.parkfifty.client.resources.ParkFiftyResources;
import com.leopin.parkfifty.client.resources.ParkFiftyResources.Style;
import com.leopin.parkfifty.client.ui.CompanyOwnerWidget;
import com.leopin.parkfifty.client.ui.CompanyWidget;
import com.leopin.parkfifty.client.ui.TextBoxCombo;
import com.leopin.parkfifty.shared.domain.CompanyProxy;
import com.leopin.parkfifty.shared.domain.CompanyUserProxy;

public class CompanyRegistrationViewImpl extends Composite implements CompanyRegistrationView, BlurHandler, FocusHandler, KeyPressHandler, HasKeyPressHandlers  {

	CompanyRegistrationPresenter presenter;
	
	private static CompanyRegistrationViewImplUiBinder uiBinder = GWT
			.create(CompanyRegistrationViewImplUiBinder.class);

	interface CompanyRegistrationViewImplUiBinder extends
			UiBinder<Widget, CompanyRegistrationViewImpl> {
	}

	public CompanyRegistrationViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		uiCompany.initBlurHandlers(this);
		uiCompany.initFocusHandlers(this);
		uiCompanyOwner.initBlurHandlers(this);
		uiCompanyOwner.initFocusHandlers(this);
		this.addKeyPressHandler(this);
	}

	
	@UiField
	CompanyWidget uiCompany;
	
	public CompanyWidget getUiCompany() {
		return uiCompany;
	}
	
	@UiField
	CompanyOwnerWidget uiCompanyOwner;
	
	public CompanyOwnerWidget getUiCompanyOwnerWidget() {
		return uiCompanyOwner;
	}
	
	@UiField
	Button uiSubmit;
	
	public Button getUiSubmit() {
		return uiSubmit;
	}
	
	@UiField
	Button uiCancel;
	
	public Button getUiCancel() {
		return uiCancel;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = (CompanyRegistrationPresenter) presenter;
	}
	
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public Style style() {
		return ParkFiftyResources.INSTANCE.style();
	}

	@Override
	public void setCompany(CompanyProxy company) {
		uiCompany.getUiName().setText(company.getName());
		uiCompany.getUiUrl().setText(company.getUrl());
		uiCompany.getUiEmail().setText(company.getEmail());
		uiCompany.getUiPriPhone().setText(company.getPriPhone());
		uiCompany.getUiSecPhone().setText(company.getSecPhone());
		uiCompany.getUiFax().setText(company.getFax());
	}

	@Override
	public void onFocus(FocusEvent event) {
		TextBox textBox = (TextBox) event.getSource();
		String name = textBox.getName();
		String text = textBox.getText();
		this.presenter.onFocusSetValue(name, text);
		
	}

	@Override
	public void onBlur(BlurEvent event) {
		TextBox textBox = (TextBox) event.getSource();
		String name = textBox.getName();
		String text = textBox.getText();
		
		this.presenter.validate(name, text);

		
	}

	@Override
	public void setUiText(String name, String value) {
		if (name.matches("uiPriPhone")) {
			uiCompany.getUiPriPhone().setText(value);
		} else if (name.matches("uiSecPhone")) {
			uiCompany.getUiSecPhone().setText(value);
		} else if (name.matches("uiFax")) {
			uiCompany.getUiFax().setText(value);
		} else if (name.matches("uiUserPriPhone")) {
			uiCompanyOwner.getUiUserPriPhone().setText(value);
		} else if (name.matches("uiUserSecPhone")) {
			uiCompanyOwner.getUiUserSecPhone().setText(value);
		} else if (name.matches("uiUserFax")) {
			uiCompanyOwner.getUiUserFax().setText(value);
		}
		
	}

	@Override
	public void showHelp(String name) {
		TextBoxCombo textBox = findTextBoxCombo(name);
		if (textBox != null) {
			textBox.showHelp();
		}	
	}

	@Override
	public void removeHelp(String name) {
		TextBoxCombo textBoxCombo = findTextBoxCombo(name);

		if (textBoxCombo != null) {
			textBoxCombo.getUiTextBox().removeStyleName(ParkFiftyResources.INSTANCE.style()
					.validateError());
			textBoxCombo.hideHelp();
		}	
		
	}
	
	/**
	 * PRIVATE METHODS
	 */
	
	/**
	 * Search for the right TextBoxCombo depending on the name 
	 * @param name Name of the widget for which the widget object has to be retrieved
	 * @return TextBoxCombo object
	 */
	private TextBoxCombo findTextBoxCombo(String name) {
		Widget widget = null;
		
		widget = uiCompany.getWidget(name);
		if (widget == null) {
			widget = uiCompanyOwner.getWidget(name);
		}
		
		if (widget instanceof TextBoxCombo) {
			return (TextBoxCombo) widget;
		} else {
			return null;
		}
	}

	@Override
	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return addDomHandler(handler, KeyPressEvent.getType());
	}

	@Override
	public void onKeyPress(KeyPressEvent event) {
		if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER)
			submit();
		event.stopPropagation();
	}

	private void submit() {
		String errorFieldName = this.presenter.submit(uiCompany.getCompany(), uiCompanyOwner.getCompanyUser());
		if (errorFieldName != null) {
			setFocus(errorFieldName);
		}
	}

//	@Override
	private void setFocus(String name) {
		Widget widget = findTextBoxCombo(name);
		if (widget != null && widget instanceof TextBoxCombo) {
			((TextBoxCombo) widget).getUiTextBox().setFocus(true);
		}
//		getUiCompany().getUiName().getUiTextBox().setFocus(true);
	}

}
