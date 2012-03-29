package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.ClientFactory;

/**
 * @author Glenn Pinto
 * Confirmation Dialog for Error messages or other messages
 *
 */
public class ConfirmationDialog extends PopupPanel {

	private static ConfirmationDialogUiBinder uiBinder = GWT
			.create(ConfirmationDialogUiBinder.class);

	interface ConfirmationDialogUiBinder extends
			UiBinder<Widget, ConfirmationDialog> {
	}
	
	ClientFactory clientFactory;
	Place place;

	public ConfirmationDialog(ClientFactory clientFactory, Place place, boolean isError) {
		this("200px", "400px");
		this.clientFactory = clientFactory;
		this.place = place;
		if (isError) {
			// TODO: Show the X image on the Confirmation dialog
		} else {
			// TODO: Show the V image on the Confirmation dialog
		}
	}
	
	public ConfirmationDialog(String height, String width) {
		setWidget(uiBinder.createAndBindUi(this));
		this.setHeight(height);
		this.setWidth(width);
		this.setGlassEnabled(true);
		this.setModal(true);
	}

	@UiField
	InlineHTML uiText;
	public InlineHTML getUiText() {
		return uiText;
	}
	
	@UiField
	InlineHTML uiCaption;
	public InlineHTML getUiCaption() {
		return uiCaption;
	}
	
	@UiField
	Button uiOK;
	public Button getUiOK() {
		return uiOK;
	}
	
	@UiHandler("uiOK")
	public void onUiOKClick(ClickEvent event) {
		this.hide();
		if (place != null) {
			clientFactory.getPlaceController().goTo(place);
		}
		
	}
	
	public void setBodyText(String text) {
		this.uiText.setText(text);
	}
	
	public void setCaption(String text) {
		this.uiCaption.setText(text);
	}

	
}
