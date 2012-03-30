package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.Place;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.ClientFactory;
import com.leopin.parkfifty.client.resources.ParkFiftyResources;

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
		setWidget(uiBinder.createAndBindUi(this));
		
		// TODO: Need to Set this as Variable, maybe small, medium and large boxes
		this.setHeight("200px");
		this.setWidth("400px");
		this.setGlassEnabled(true);
		this.setModal(true);
		this.clientFactory = clientFactory;
		this.place = place;
		if (isError) {
			uiImage.setResource(ParkFiftyResources.INSTANCE.errorIcon());
		} else {
			uiImage.setResource(ParkFiftyResources.INSTANCE.successIcon());
		}
	}
	
	public ConfirmationDialog(String height, String width) {

	}

//	@UiField
//	InlineHTML uiText;
//	public InlineHTML getUiText() {
//		return uiText;
//	}
	
//	@UiField
//	InlineHTML uiCaption;
//	public InlineHTML getUiCaption() {
//		return uiCaption;
//	}
	
	@UiField
	Image uiImage;
	public Image getUiImage() {
		return uiImage;
	}
	
	@UiField
	SpanElement uiText;
	public SpanElement getUiText() {
		return uiText;
	}
	
	@UiField
	SpanElement uiCaption;
	public SpanElement getUiCaption() {
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
//		this.uiText.setText(text);
		this.uiText.setInnerText(text);
	}
	
	public void setCaption(String text) {
//		this.uiCaption.setText(text);
		this.uiCaption.setInnerText(text);
	}
	

	
}
