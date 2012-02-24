package com.leopin.parkfifty.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.constants.AppConstants;
import com.leopin.parkfifty.client.resources.ParkFiftyResources;

/**
 * Custom TextBox that bundle an Input Box, Exclamation Icon to be displayed
 * when there are validation errors and its corresponding help text. The Input
 * box will highlight if there are any validation errors
 * 
 * @author Glenn Pinto
 *
 */
public class TextBoxCombo extends Composite implements HasFocusHandlers, HasBlurHandlers {
//public class TextBoxCombo extends Composite  {

	private static TextBoxComboUiBinder uiBinder = GWT.create(TextBoxComboUiBinder.class);
	
	private ErrorHelpTextPopup errorHelpTextPopup;

	interface TextBoxComboUiBinder extends UiBinder<Widget, TextBoxCombo> {
	}

	public TextBoxCombo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	TextBox uiTextBox;
	
	@UiField
	Image uiImage;
	
	String helpText = "";
	
	// Initialize the Width and Height of the Popup help text
	private int helpWidth = AppConstants.INSTANCE.helpTextWidth();
	private int helpHeight = AppConstants.INSTANCE.helpTextHeight();

	public TextBoxCombo(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	
	}

	/**
	 * Set the Contents of the Input Text Box
	 * @param text
	 */
	public void setText(String text) {
		uiTextBox.setText(text);
	}
	
	/**
	 * Get the Contents of the Input TextBox
	 * @return
	 */
	public String getText() {
		return uiTextBox.getText();
	}
	
	/**
	 * Set the Max Length of the Input TextBox
	 * @param maxLength
	 */
	public void setMaxLength(String maxLength) {
		uiTextBox.setMaxLength(Integer.valueOf(maxLength));
	}
	
	/**
	 * Set the Visible Length of the Input TextBox
	 * @param visibleLength
	 */
	public void setVisibleLength(String visibleLength) {
		uiTextBox.setVisibleLength(Integer.valueOf(visibleLength));
	}
	
	/**
	 * Get the name of the Input TextBox
	 * @return
	 */
	public String getName() {
		return uiTextBox.getName();
	}
	
	/**
	 * Set the name of the Input TextBox
	 * @param name
	 */
	public void setName(String name) {
		uiTextBox.setName(name);
	}
	

	/**
	 * Set the Width of the Popup help box
	 * @param helpWidth
	 */
	public void setHelpWidth(int helpWidth) {
		this.helpWidth = helpWidth;
	}

	/**
	 * Set the Height of the Popup help box
	 * @param helpHeight
	 */
	public void setHelpHeight(int helpHeight) {
		this.helpHeight = helpHeight;
	}
	
	/**
	 * Add a style to the Input TextBox
	 */
	public void addStyleName(String style) {
		uiTextBox.addStyleName(style);
	}
	
	/**
	 * Set Help Text to be set for the popup
	 * @param helpText
	 */
	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}


	/**
	 * Capture the MouseOver event and popup the help text
	 * @param e event
	 */
	@UiHandler("uiImage")
	void onMouseOver(MouseOverEvent e) {
		if (errorHelpTextPopup == null) {
			errorHelpTextPopup = new ErrorHelpTextPopup(helpText);
			errorHelpTextPopup.addStyleName(popupStyle());
//			errorHelpTextPopup.setAnimationEnabled(true);
//			errorHelpTextPopup.setPixelSize(helpWidth, helpHeight);
			errorHelpTextPopup.setSize(String.valueOf(helpWidth) + "px", "100%");
			
			int tempLeft = uiImage.getAbsoluteLeft() + uiImage.getOffsetWidth();
			
			// 20 is the buffer
			if ((tempLeft + helpWidth + 20) > Window.getClientWidth()) {
				tempLeft = uiImage.getAbsoluteLeft() - (helpWidth) - uiImage.getOffsetWidth();
			}
			final int left = tempLeft;
			final int top = uiImage.getAbsoluteTop() + uiImage.getOffsetHeight();
//			final int top = e.getClientY();
//			final int left = e.getClientX();
			errorHelpTextPopup.setPopupPositionAndShow(new PopupPanel.PositionCallback() {

				public void setPosition(int offsetWidth, int offsetHeight) {
					errorHelpTextPopup.setPopupPosition(left, top);
					
				}
			});
		}
	}
	
	@UiHandler("uiImage")
	public void onMouseOut(MouseOutEvent e) {
		if (errorHelpTextPopup != null) {
			errorHelpTextPopup.removeFromParent();
			errorHelpTextPopup = null;
		}
	}

	/**
	 * Popup for the help text for validation errors
	 */
	public static class ErrorHelpTextPopup extends PopupPanel {
		Label helpTextLabel = new Label();
		public ErrorHelpTextPopup(String helpText) {
			super(false);
			setModal(false);
			helpTextLabel.setText(helpText);
			setWidget(helpTextLabel);
		}

		public void setHelpText(String helpText) {
			helpTextLabel.setText(helpText);
		}
	}

	/**
	 * Handler registration to handle onFocus Events
	 */
	@Override
	public HandlerRegistration addFocusHandler(FocusHandler handler) {
		return uiTextBox.addFocusHandler(handler);
	}

	/**
	 * Handler registration to handle onBlur Events
	 */
	@Override
	public HandlerRegistration addBlurHandler(BlurHandler handler) {
		return uiTextBox.addBlurHandler(handler);
	}
	
	/**
	 * Show the help icon so that the help text popup is reachable
	 */
	public void showHelp() {
		uiImage.setVisible(true);
	}
	
	/**
	 * hide the help icon so the help text popup is not reachable
	 */
	public void hideHelp() {
		uiImage.setVisible(false);
	}
	
	private String popupStyle() {
		return ParkFiftyResources.INSTANCE.style().popup();
	}


}
