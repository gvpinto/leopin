package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ConfirmationDialog  {

	private static ConfirmationDialogUiBinder uiBinder = GWT
			.create(ConfirmationDialogUiBinder.class);

	interface ConfirmationDialogUiBinder extends
			UiBinder<Widget, ConfirmationDialog> {
	}

	public ConfirmationDialog(String message) {
		GWT.log("Initializing Confirmation Dialog");
		uiBinder.createAndBindUi(this);
		this.uiMessage.setText(message);
		this.uiErrorDialog.setGlassEnabled(true);
		// TODO Try to set it up as MODAL and not responsive to out side clicks
	}
	
	@UiField
	HTML uiMessage;
	
	@UiField
	DialogBox uiErrorDialog;
	
	@UiField
	Button uiOk;
	
	@UiHandler("uiOk")
	public void onOkClick(ClickEvent event) {
		this.uiErrorDialog.hide();
	}
	
	public void show() {
		this.uiErrorDialog.show();
	}

}
