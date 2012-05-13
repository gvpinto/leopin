package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.resources.AppStyles;
import com.leopin.parkfifty.client.resources.AppStyles.AppResources;
import com.leopin.parkfifty.client.resources.AppStyles.Style;
import com.leopin.parkfifty.shared.messages.FieldLabels;
import com.leopin.parkfifty.shared.messages.ValidationMessages;

public class LoginDialog extends PopupPanel implements UiWidget, HasKeyPressHandlers  {

	private static LoginDialogUiBinder uiBinder = GWT
			.create(LoginDialogUiBinder.class);

	interface LoginDialogUiBinder extends UiBinder<Widget, LoginDialog> {
	}

	public LoginDialog() {
		setWidget(uiBinder.createAndBindUi(this));
		
		// Set the Help Text in advance from the AppMessages properties file
		uiUsername.setHelpText(validationMessages().userIdInvalid());
		uiPassword.setHelpText(validationMessages().passwordInvalid());
		uiCaption.setInnerText("Login");
		
		// Set the Image
		uiImage.setResource(resources().lockIcon());
		
		// TODO: Need to Set this as Variable, maybe small, medium and large boxes
		this.setHeight("300");
		this.setWidth("350px");
		this.setGlassEnabled(true);
		this.setModal(true);
	}
	
	@UiField
	Image uiImage;
	Image getUiImage() {
		return uiImage;
	}

	@UiField
	SpanElement uiCaption;
	SpanElement getUiCaption() {
		return uiCaption;
	}
	
	@UiField
	TextBoxCombo uiUsername;
	public TextBoxCombo getUiUsername() {
		return uiUsername;
	}
	
	@UiField
	TextBoxCombo uiPassword;
	public TextBoxCombo getUiPassword() {
		return uiPassword;
	}
	
	@UiField
	Button uiLogin;
	public Button getUiLogin() {
		return uiLogin;
	}
	
	@UiField
	Button uiCancel;
	public Button getUiCancel() {
		return uiCancel;
	}
	
	@UiField
	SpanElement uiErrorMsg;
	public SpanElement getUiErrorMsg() {
		return uiErrorMsg;
	}
	
	public void clear() {
		uiUsername.clear();
		uiPassword.clear();
	}
	
	public void setErrorMsg(String message) {
		uiErrorMsg.setInnerText(message);
	}

	@Override
	public void initHandlers(EventHandler handler) {
		uiUsername.addFocusHandler((FocusHandler)handler);
		uiUsername.addBlurHandler((BlurHandler)handler);
		uiPassword.addFocusHandler((FocusHandler)handler);
		uiPassword.addBlurHandler((BlurHandler)handler);
		uiLogin.addClickHandler((ClickHandler) handler);
		uiCancel.addClickHandler((ClickHandler) handler);
		this.addKeyPressHandler((KeyPressHandler) handler);
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
	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return addDomHandler(handler, KeyPressEvent.getType());
	}
	

}
