package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginDialog extends PopupPanel   {

	private static LoginDialogUiBinder uiBinder = GWT
			.create(LoginDialogUiBinder.class);

	interface LoginDialogUiBinder extends UiBinder<Widget, LoginDialog> {
	}

	public LoginDialog() {
		setWidget(uiBinder.createAndBindUi(this));
		// TODO: Need to Set this as Variable, maybe small, medium and large boxes
		this.setHeight("300");
		this.setWidth("450px");
		this.setGlassEnabled(true);
		this.setModal(true);
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
	
	public void clear() {
		uiUsername.clear();
		uiPassword.clear();
	}

}
