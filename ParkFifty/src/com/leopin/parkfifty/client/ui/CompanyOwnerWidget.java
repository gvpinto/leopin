package com.leopin.parkfifty.client.ui;

import static com.leopin.parkfifty.shared.constants.CompanyUserFields.*;

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
import com.leopin.parkfifty.shared.messages.ValidationMessages;

public class CompanyOwnerWidget extends Composite implements UiWidget {

	private static CompanyOwnerWidgetUiUserBinder uiBinder = GWT
			.create(CompanyOwnerWidgetUiUserBinder.class);

	interface CompanyOwnerWidgetUiUserBinder extends
			UiBinder<Widget, CompanyOwnerWidget> {
	}

	public CompanyOwnerWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		
		// Set the Help Text in advance from the AppMessages properties file
		uiUserUsername.setHelpText(ValidationMessages.INSTANCE.userIdInvalid());
		uiUserPassword.setHelpText(ValidationMessages.INSTANCE.passwordInvalid());
		uiUserTitle.setHelpText(ValidationMessages.INSTANCE.titleInvalid());
		uiUserFirstName.setHelpText(ValidationMessages.INSTANCE.firstNameInvalid());
		uiUserMiddleInitial.setHelpText(ValidationMessages.INSTANCE.middleInitialInvalid());
		uiUserLastName.setHelpText(ValidationMessages.INSTANCE.lastNameInvalid());
		uiUserSuffix.setHelpText(ValidationMessages.INSTANCE.suffixInvalid());
		uiUserPriPhone.setHelpText(ValidationMessages.INSTANCE.priPhoneNumInvalid());
		uiUserSecPhone.setHelpText(ValidationMessages.INSTANCE.secPhoneNumInvalid());
		uiUserFax.setHelpText(ValidationMessages.INSTANCE.faxInvalid());
		uiUserEmail.setHelpText(ValidationMessages.INSTANCE.emailInvalid());
		
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
	TextBoxCombo uiUserUsername;
	
	public TextBoxCombo getUiUserUsername() {
		return uiUserUsername;
	}
	
	@UiField
	PasswordCombo uiUserPassword;
	
	public PasswordCombo getUiUserPassword() {
		return uiUserPassword;
	}
	
	@UiField
	TextBoxCombo uiUserTitle;
	
	public TextBoxCombo getUiUserTitle() {
		return uiUserTitle;
	}
	
	@UiField
	TextBoxCombo uiUserFirstName;
	
	public TextBoxCombo getUiUserFirstName() {
		return uiUserFirstName;
	}
	
	@UiField
	TextBoxCombo uiUserMiddleInitial;
	
	public TextBoxCombo getUiUserMiddleInitial() {
		return uiUserMiddleInitial;
	}
	
	@UiField
	TextBoxCombo uiUserLastName;
	
	public TextBoxCombo getUiUserLastName() {
		return uiUserLastName;
	}
	
	@UiField
	TextBoxCombo uiUserSuffix;
	
	public TextBoxCombo getUiUserSuffix() {
		return uiUserSuffix;
	}
	
	@UiField
	TextBoxCombo uiUserPriPhone;
	
	public TextBoxCombo getUiUserPriPhone() {
		return uiUserPriPhone;
	}
	
	@UiField
	TextBoxCombo uiUserSecPhone;
	
	public TextBoxCombo getUiUserSecPhone() {
		return uiUserSecPhone;
	}
	
	@UiField
	TextBoxCombo uiUserFax;
	
	public TextBoxCombo getUiUserFax() {
		return uiUserFax;
	}
	
	@UiField
	TextBoxCombo uiUserEmail;
	
	public TextBoxCombo getUiUserEmail() {
		return uiUserEmail;
	}
	
	/**
	 * Initialize Focus Handlers for Composites using this widget
	 * @param handler FocusHandler
	 */
	public void initFocusHandlers(FocusHandler handler) {
		getUiUserUsername().addFocusHandler(handler);
		getUiUserPassword().addFocusHandler(handler);
		getUiUserTitle().addFocusHandler(handler);
		getUiUserFirstName().addFocusHandler(handler);
		getUiUserMiddleInitial().addFocusHandler(handler);
		getUiUserLastName().addFocusHandler(handler);
		getUiUserSuffix().addFocusHandler(handler);
		getUiUserPriPhone().addFocusHandler(handler);
		getUiUserSecPhone().addFocusHandler(handler);
		getUiUserFax().addFocusHandler(handler);
		getUiUserEmail().addFocusHandler(handler);
	}
	
	/**
	 * Initialize Blur Handler for Composites using this widget
	 * @param handler BlurHandler
	 */
	public void initBlurHandlers(BlurHandler handler) {
		getUiUserUsername().addBlurHandler(handler);
		getUiUserPassword().addBlurHandler(handler);
		getUiUserTitle().addBlurHandler(handler);
		getUiUserFirstName().addBlurHandler(handler);
		getUiUserMiddleInitial().addBlurHandler(handler);
		getUiUserLastName().addBlurHandler(handler);
		getUiUserSuffix().addBlurHandler(handler);
		getUiUserPriPhone().addBlurHandler(handler);
		getUiUserSecPhone().addBlurHandler(handler);
		getUiUserFax().addBlurHandler(handler);
		getUiUserEmail().addBlurHandler(handler);
	}
	
	public CompanyUserProxy getCompanyUser() {
		CompanyUserProxy companyUserProxy = new CompanyUserProxyImpl();
		companyUserProxy.setUsername(getUiUserUsername().getText());
		companyUserProxy.setPassword(getUiUserPassword().getText());
		companyUserProxy.setTitle(getUiUserTitle().getText());
		companyUserProxy.setFirstName(getUiUserFirstName().getText());
		companyUserProxy.setMiddleInitial(getUiUserMiddleInitial().getText());
		companyUserProxy.setLastName(getUiUserLastName().getText());
		companyUserProxy.setSuffix(getUiUserSuffix().getText());
		companyUserProxy.setPriPhone(getUiUserPriPhone().getText());
		companyUserProxy.setSecPhone(getUiUserSecPhone().getText());
		companyUserProxy.setFax(getUiUserFax().getText());
		companyUserProxy.setEmail(getUiUserEmail().getText());
		
		return companyUserProxy;
	}

	public Widget getWidget(String name) {
		Widget widget = null;
		
		if (name.matches(UiUsername.getId())) {
			widget = this.getUiUserUsername();
		} else if (name.matches(UiPassword.getId())) {
			widget = this.getUiUserPassword();
		} else if (name.matches(UiEmail.getId())) {
			widget = this.getUiUserEmail();
		} else if (name.matches(UiPriPhone.getId())) {
			widget = this.getUiUserPriPhone();
		} else if (name.matches(UiSecPhone.getId())) {
			widget = this.getUiUserSecPhone();
		} else if (name.matches(UiFax.getId())) {
			widget = this.getUiUserFax();
		} else if (name.matches(UiTitle.getId())) {
			widget = this.getUiUserTitle();
		} else if (name.matches(UiFirstName.getId())) {
			widget = this.getUiUserFirstName();
		} else if (name.matches(UiLastName.getId())) {
			widget = this.getUiUserLastName();
		} else if (name.matches(UiMiddleInitial.getId())) {
			widget = this.getUiUserMiddleInitial();
		} else if (name.matches(UiSuffix.getId())) {
			widget = this.getUiUserSuffix();
		}
		return widget;
	}
	@Override
	public Style style() {
		return ParkFiftyResources.INSTANCE.style();
	}
	
}
