package com.leopin.parkfifty.client.ui;

import static com.leopin.parkfifty.shared.constants.CompanyUserFields.UiEmail;
import static com.leopin.parkfifty.shared.constants.CompanyUserFields.UiFax;
import static com.leopin.parkfifty.shared.constants.CompanyUserFields.UiFirstName;
import static com.leopin.parkfifty.shared.constants.CompanyUserFields.UiLastName;
import static com.leopin.parkfifty.shared.constants.CompanyUserFields.UiMiddleInitial;
import static com.leopin.parkfifty.shared.constants.CompanyUserFields.UiPassword;
import static com.leopin.parkfifty.shared.constants.CompanyUserFields.UiPriPhone;
import static com.leopin.parkfifty.shared.constants.CompanyUserFields.UiSecPhone;
import static com.leopin.parkfifty.shared.constants.CompanyUserFields.UiSuffix;
import static com.leopin.parkfifty.shared.constants.CompanyUserFields.UiTitle;
import static com.leopin.parkfifty.shared.constants.CompanyUserFields.UiUsername;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.domain.CompanyUserProxyImpl;
import com.leopin.parkfifty.client.resources.AppStyles;
import com.leopin.parkfifty.client.resources.AppStyles.AppResources;
import com.leopin.parkfifty.client.resources.AppStyles.Style;
import com.leopin.parkfifty.shared.domain.CompanyUserProxy;
import com.leopin.parkfifty.shared.messages.FieldLabels;
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
		uiUsername.setHelpText(validationMessages().userIdInvalid());
		uiPassword.setHelpText(validationMessages().passwordInvalid());
		uiUserTitle.setHelpText(validationMessages().titleInvalid());
		uiUserFirstName.setHelpText(validationMessages().firstNameInvalid());
		uiUserMiddleInitial.setHelpText(validationMessages().middleInitialInvalid());
		uiUserLastName.setHelpText(validationMessages().lastNameInvalid());
		uiUserSuffix.setHelpText(validationMessages().suffixInvalid());
		uiUserPriPhone.setHelpText(validationMessages().priPhoneNumInvalid());
		uiUserSecPhone.setHelpText(validationMessages().secPhoneNumInvalid());
		uiUserFax.setHelpText(validationMessages().faxInvalid());
		uiUserEmail.setHelpText(validationMessages().emailInvalid());
		
		// Size the 2nd column on the grid to avoid the jumping of the table 
		// when the help icon is displayed
		uiGrid.getColumnFormatter().addStyleName(0, this.style().labelCol());
		uiGrid.getColumnFormatter().addStyleName(1, this.style().fieldCol());
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
		getUiUsername().addFocusHandler(handler);
		getUiPassword().addFocusHandler(handler);
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
		getUiUsername().addBlurHandler(handler);
		getUiPassword().addBlurHandler(handler);
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
		companyUserProxy.setUsername(getUiUsername().getText());
		companyUserProxy.setPassword(getUiPassword().getText());
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
			widget = this.getUiUsername();
		} else if (name.matches(UiPassword.getId())) {
			widget = this.getUiPassword();
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
	
	public void clear() {
		this.getUiUsername().setText("");
		this.getUiPassword().setText("");
		this.getUiUserTitle().setText("");
		this.getUiUserFirstName().setText("");
		this.getUiUserLastName().setText("");
		this.getUiUserMiddleInitial().setText("");
		this.getUiUserEmail().setText("");
		this.getUiUserPriPhone().setText("");
		this.getUiUserSecPhone().setText("");
		this.getUiUserSuffix().setText("");
		this.getUiUserFax().setText("");
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
	
}
