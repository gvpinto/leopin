package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author gvpinto
 *
 */
public class ConfirmationDialog extends PopupPanel  {

	Button uiButton;

	public ConfirmationDialog(String message) {
		GWT.log("Initializing Confirmation Dialog");
		this.setTitle("Error");
		this.uiButton = new Button();
		uiButton.setText("OK");
		this.setGlassEnabled(true);
		this.setModal(true);
//		this.uiErrorDialog.setWidth("400px");
//		this.uiErrorDialog.setHeight("300px");
		// TODO Try to set it up as MODAL and not responsive to out side clicks
	}
	
	/**
	 * Center the Error Dialog and display the error message
	 */
	public void displayError() {
		this.setPopupPositionAndShow(new PositionCallback() {
			
			@Override
			public void setPosition(int offsetWidth, int offsetHeight) {
				int left = (Window.getClientWidth() - offsetWidth)/2;
				if (left < 0) {
					left = 0;
				}
				
				int top = (Window.getClientHeight() - offsetHeight)/2;
				if (top < 0) {
					top = 0;
				}

				setPopupPosition(left, top);		
				setWidth("400px");
				setHeight("300px");
			}
		});
		
		
	}
	
}
