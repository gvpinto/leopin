package com.leopin.parkfifty.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.presenters.HomePresenter;
import com.leopin.parkfifty.client.presenters.Presenter;
import com.leopin.parkfifty.client.resources.ParkFiftyResources;
import com.leopin.parkfifty.client.ui.CompanyWidget;
import com.leopin.parkfifty.client.ui.TextBoxCombo;
import com.leopin.parkfifty.shared.domain.Company;

public class HomeViewImpl extends Composite implements HomeView, FocusHandler,
		BlurHandler, KeyPressHandler {

	private HomePresenter presenter;

	private static HomeViewImplUiBinder uiBinder = GWT
			.create(HomeViewImplUiBinder.class);

	interface HomeViewImplUiBinder extends UiBinder<Widget, HomeViewImpl> {
	}

	public HomeViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		uiCompanyWidget.initBlurHandlers(this);
		uiCompanyWidget.initFocusHandlers(this);
		uiContinue.getElement().setId("uiContinue");
	}

	@UiField
	CompanyWidget uiCompanyWidget;

	@UiField
	Button uiContinue;

//	public HomeViewImpl(String firstName) {
//		initWidget(uiBinder.createAndBindUi(this));
//	}

	@UiHandler("uiContinue")
	void onClick(ClickEvent e) {
		next();
	}

	@Override
	public void onKeyPress(KeyPressEvent event) {
		if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER)
			next();
		event.stopPropagation();
	}
	
	@Override
	public void onFocus(FocusEvent event) {
		GWT.log("HomeViewImpl - onBlur: " + event.getSource().toString());
		TextBox textBox = (TextBox) event.getSource();
		textBox.removeStyleName(ParkFiftyResources.INSTANCE.style()
				.validateError());
		((TextBoxCombo) textBox.getParent().getParent()).hideHelp();
		if (textBox.getName().matches("uiPriPhone|uiFax|uiSecPhone")) {

		}
	}

	/**
	 * Set the error style to show the exclamation icon and display the error
	 * message
	 * 
	 * @param textBox
	 */
	private void showHelp(TextBox textBox) {
		textBox.addStyleName(errorStyle());
		((TextBoxCombo) textBox.getParent().getParent()).showHelp();
	}

	@Override
	public void onBlur(BlurEvent event) {
		// GWT.log("HomeViewImpl - onFocus: " + event.getSource().toString());
		// Validation
		TextBox textBox = (TextBox) event.getSource();
		if (textBox.getName().matches("uiName")) {
			if (!this.presenter.validateName(textBox.getText())) {
				showHelp(textBox);
			}
		} else if (textBox.getName().matches("uiUrl")) {
			if (!this.presenter.validateUrl(textBox.getText())) {
				showHelp(textBox);
			}
		} else if (textBox.getName().matches("uiEmail")) {
			if (!this.presenter.validateEmail(textBox.getText())) {
				showHelp(textBox);
			}
		} else if (textBox.getName().matches("uiPriPhone")) {
			boolean valid = true;
			textBox.setText(this.presenter.stripChars(textBox.getText()));
			if (!this.presenter.validatePriPhone(textBox.getText())) {
				;
				showHelp(textBox);
				valid = false;
			}
			// Format the Phone Number if he entered data is valid
			if (valid) {
				textBox.setText(this.presenter.formatPhoneNum(textBox.getText()));
			}
		} else if (textBox.getName().matches("uiFax|uiSecPhone")) {
			boolean valid = true;
			textBox.setText(this.presenter.stripChars(textBox.getText()));
			if (!this.presenter.validateOtherPhone(textBox.getText())) {
				showHelp(textBox);
				valid = false;
			}
			// Format the Phone Number if he entered data is valid
			if (valid) {
				textBox.setText(this.presenter.formatPhoneNum(textBox.getText()));
			}
		}
	}


	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = (HomePresenter) presenter;
	}

	@Override
	public Widget asWidget() {
		return this;
	}
	
	/*
	 * PRIVATE METHODS
	 */
	private void next() {
		this.presenter.next(uiCompanyWidget.getCompany());
	}


	/**
	 * Get access to the resource file
	 * 
	 * @return
	 */
	private String errorStyle() {
		return ParkFiftyResources.INSTANCE.style().validateError();
	}
}
