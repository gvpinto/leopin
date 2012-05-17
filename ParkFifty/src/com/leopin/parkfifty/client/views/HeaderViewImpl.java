package com.leopin.parkfifty.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.presenters.HeaderPresenter;
import com.leopin.parkfifty.client.presenters.Presenter;
import com.leopin.parkfifty.client.resources.AppStyles;
import com.leopin.parkfifty.client.resources.AppStyles.AppResources;
import com.leopin.parkfifty.client.resources.AppStyles.Style;
import com.leopin.parkfifty.client.ui.LoginDialog;
import com.leopin.parkfifty.client.ui.TextBoxBaseCombo;
import com.leopin.parkfifty.client.ui.TopNav;
import com.leopin.parkfifty.shared.constants.CompanyUserFields;
import com.leopin.parkfifty.shared.constants.NavigationButtons;
import com.leopin.parkfifty.shared.messages.FieldLabels;
import com.leopin.parkfifty.shared.messages.ValidationMessages;

/**
 * Defined a view to be the top nav. This is not defined as a composite
 * and avoid an unnecessary <DIV>
 * @author Glenn Pinto
 *
 */
public class HeaderViewImpl  implements HeaderView, ClickHandler, FocusHandler, BlurHandler, KeyPressHandler {

	HeaderPresenter presenter;
	TopNav topNav;
	LoginDialog loginDialog;
	boolean loginSuccessFul;
	
	public HeaderViewImpl() {
		loginDialog = new LoginDialog();
		loginDialog.setVisible(false);
		topNav = new TopNav();

//		topNav.initHandlers(this);
//		loginDialog.getUiLogin().addClickHandler(this);
		// Initialize Focus, Blur and ClickHandlers
		loginDialog.initHandlers(this);
		topNav.initHandlers(this);
		
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = (HeaderPresenter) presenter;
	}

	@Override
	public Widget asWidget() {
		return topNav;
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
	public void onClick(ClickEvent event) {
		Object source = event.getSource();
		GWT.log(source.toString());
		if (source instanceof Anchor) {
			Anchor uiLoginAnchor = (Anchor) source;
			loginDialog.center();
			// Clear the text fields and any help text lingering from the previous validation
			loginDialog.clear();
			//setFocus(CompanyUserFields.UiUsername.getId());
		} else if(source instanceof Button) {
			Button button = (Button) source;
			if ("Login".equals(button.getText())) {
				attemptToLogin();
			} else if("Cancel".equals(button.getText())) {
				loginDialog.hide();
			}
		}
		

	}

	// -- PRIVATE METHODS --
	/**
	 * Attempt to Login when the Login Button is Clicked
	 */
	private void attemptToLogin() {

		String name = this.presenter.attemptToLogin(
				loginDialog.getUiUsername().getText(), 
				loginDialog.getUiPassword().getText());
		
		// Set the focus on the field the failed the validation
		if (name != null) {
			setFocus(name);
		} else {
			setFocus(CompanyUserFields.UiUsername.getId());
		}
		
		// Clear the entered credentials after failed a login attempt
		loginDialog.clear();
		
	}
	

	private void setFocus(String name) {
		Widget widget = findTextBoxCombo(name);
		if (widget != null && widget instanceof TextBoxBaseCombo) {
			TextBoxBaseCombo textBoxCombo = (TextBoxBaseCombo) widget;
			textBoxCombo.getUiTextBox().selectAll();
			textBoxCombo.getUiTextBox().setFocus(true);
		}
	}

	@Override
	public void setErrorMsg(String message) {
		// Set the error message, clear the username and password fields and set the focus on the username field
		loginDialog.setErrorMsg(message);
//		loginDialog.clear();
//		setFocus(CompanyUserFields.UiUsername.getId());

		
	}
	
	public void clearErrorMsg() {
		loginDialog.setErrorMsg("");
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
	public void onFocus(FocusEvent event) {
		clearErrorMsg();
	}

	@Override
	public void setLoginText(boolean loginSuccessful) {

		this.loginSuccessFul = loginSuccessful;
		/* 
		 * When the loading the home page check if the user is already authenticated.
		 * If Authenticated then show the Log Off link
		 *  If Not Authenticated the display the Log In Link and also the sign up fields
		 */
		if (loginSuccessful) {
			topNav.setToLogOff();
		} else {
			topNav.setToLogin();
		}
		
	}
	

	@Override
	public void showHelp(String name) {
		TextBoxBaseCombo textBox = findTextBoxCombo(name);
		if (textBox != null) {
			textBox.showError();
		}	
	}

	@Override
	public void removeHelp(String name) {
		TextBoxBaseCombo textBoxCombo = findTextBoxCombo(name);

		if (textBoxCombo != null) {
			textBoxCombo.getUiTextBox().removeStyleName(style()
					.validateError());
			textBoxCombo.hideError();
		}	
		
	}
	

	/**
	 * Search for the right TextBoxCombo depending on the name 
	 * @param name Name of the widget for which the widget object has to be retrieved
	 * @return TextBoxCombo object
	 */
	private TextBoxBaseCombo findTextBoxCombo(String name) {
		Widget widget = null;
		
		if (CompanyUserFields.UiUsername.getId().equalsIgnoreCase(name)) {
			return loginDialog.getUiUsername();
		} else if(CompanyUserFields.UiPassword.getId().equalsIgnoreCase(name)) {
			return loginDialog.getUiPassword();
		} else {
			return null;
		}
	}


	@Override
	public void onBlur(BlurEvent event) {
		
		// Validate Username and Password
		TextBox textBox = (TextBox) event.getSource();
		String name = textBox.getName();
		String text = textBox.getText();
		
		this.presenter.validate(name, text);
		
	}

	@Override
	public void onKeyPress(KeyPressEvent event) {
		// Handle enter key on the username and password fields
		if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER)
			attemptToLogin();
		event.stopPropagation();
		
		
	}

	@Override
	public void initialize() {
		// Nothing to initialize
		
	}
	
	

}
