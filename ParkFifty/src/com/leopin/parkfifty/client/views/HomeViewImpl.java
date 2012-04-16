package com.leopin.parkfifty.client.views;

import static com.leopin.parkfifty.shared.constants.CompanyFields.UiFax;
import static com.leopin.parkfifty.shared.constants.CompanyFields.UiPriPhone;
import static com.leopin.parkfifty.shared.constants.CompanyFields.UiSecPhone;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.presenters.HomePresenter;
import com.leopin.parkfifty.client.presenters.Presenter;
import com.leopin.parkfifty.client.resources.AppStyles;
import com.leopin.parkfifty.client.resources.AppStyles.AppResources;
import com.leopin.parkfifty.client.resources.AppStyles.Style;
import com.leopin.parkfifty.client.ui.CompanyWidget;
import com.leopin.parkfifty.client.ui.TextBoxBaseCombo;
import com.leopin.parkfifty.client.ui.TextBoxCombo;
import com.leopin.parkfifty.shared.messages.ValidationMessages;


public class HomeViewImpl extends Composite implements HomeView, FocusHandler,
		BlurHandler, KeyPressHandler, HasKeyPressHandlers {

	private HomePresenter presenter;

	private static HomeViewImplUiBinder uiBinder = GWT
			.create(HomeViewImplUiBinder.class);

	interface HomeViewImplUiBinder extends UiBinder<Widget, HomeViewImpl> {
	}

	public HomeViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));		
		
		// Initialize the handlers
		uiCompanyWidget.initBlurHandlers(this);
		uiCompanyWidget.initFocusHandlers(this);
		
		// Set the id of the Continue button to uiContinue
		uiContinue.getElement().setId("uiContinue");
		
		// Handler the Enter key to submit the form
		this.addKeyPressHandler(this);
	}

	@UiField
	CompanyWidget uiCompanyWidget;

	@UiField
	Button uiContinue;

	@UiHandler("uiContinue")
	void onClick(ClickEvent e) {
		next();
	}

	
	@Override
	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return addDomHandler(handler, KeyPressEvent.getType());
	}
	
	@Override
	public void onKeyPress(KeyPressEvent event) {
		if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER)
			next();
		event.stopPropagation();
	}
	
	@Override
	public void onFocus(FocusEvent event) {
		
		TextBox textBox = (TextBox) event.getSource();
		String name = textBox.getName();
		String text = textBox.getText();
		this.presenter.onFocusSetValue(name, text);
		
//		TextBox textBox = (TextBox) event.getSource();
//		textBox.removeStyleName(ParkFiftyResources.INSTANCE.style()
//				.validateError());
//		((TextBoxCombo) textBox.getParent().getParent()).hideHelp();
//		if (textBox.getName().matches("uiPriPhone|uiFax|uiSecPhone")) {
//
//		}
	}


	@Override
	public void onBlur(BlurEvent event) {
		// GWT.log("HomeViewImpl - onFocus: " + event.getSource().toString());
		// Validation
		TextBox textBox = (TextBox) event.getSource();
		String name = textBox.getName();
		String text = textBox.getText();
		
		this.presenter.validate(name, text);

	}


	public void showHelpUiName(String text) {
		uiCompanyWidget.getUiName().showHelp();
	}

	
//	public void showHelpUiUrl(String text) {
//		uiCompanyWidget.getUiUrl().showHelp();
//	}
//	
//	public void showHelpUiEmail(String text) {
//		uiCompanyWidget.getUiEmail().showHelp();
//	}
//	
//	public void showHelpUiPriPhone(String text) {
//		uiCompanyWidget.getUiPriPhone().showHelp();
//	}
//	
//	public void showHelpUiSecPhone(String text) {
//		uiCompanyWidget.getUiSecPhone().showHelp();
//	}
//	
//	public void showHelpUiFax(String text) {
//		uiCompanyWidget.getUiFax().showHelp();
//	}


	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = (HomePresenter) presenter;	
	}

	@Override
	public Widget asWidget() {
		return this;
	}


	@Override
	public void setUiText(String name, String text) {
		
		if (name.matches(UiPriPhone.getId())) {
			uiCompanyWidget.getUiPriPhone().setText(text);
		} else if (name.matches(UiSecPhone.getId())) {
			uiCompanyWidget.getUiSecPhone().setText(text);
		} else if (name.matches(UiFax.getId())) {
			uiCompanyWidget.getUiFax().setText(text);
		}
		
	}

	/**
	 * Remove the help and any highlighting associated with the help
	 */
	public void removeHelp(String name) {
		
		TextBoxBaseCombo textBoxCombo = findTextBoxCombo(name);

		if (textBoxCombo != null) {
			textBoxCombo.getUiTextBox().removeStyleName(style()
					.validateError());
			textBoxCombo.hideHelp();
		}

	}
	
	/**
	 * Show help related artifacts and highlighting to indicate an error
	 */
	public void showHelp(String name) {
		TextBoxBaseCombo textBox = findTextBoxCombo(name);
		if (textBox != null) {
			textBox.showHelp();
		}	
	}
	@Override
	public void setFocus(String name) {
		Widget widget = findTextBoxCombo(name);
		if (widget != null && widget instanceof TextBoxCombo) {
			TextBoxCombo textBoxCombo = (TextBoxCombo) widget;
			textBoxCombo.getUiTextBox().selectAll();
			textBoxCombo.getUiTextBox().setFocus(true);
		}
//		uiCompanyWidget.getUiName().getUiTextBox().setFocus(true);
		
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
	
	/*
	 * PRIVATE METHODS
	 */
	private void next() {
		String errorFieldName = this.presenter.next(uiCompanyWidget.getCompany());
		if (errorFieldName != null) {
			setFocus(errorFieldName);
		}
	}

	/**
	 * Search for the right TextBoxCombo depending on the name 
	 * @param name Name of the widget for which the widget object has to be retrieved
	 * @return TextBoxCombo object
	 */
	private TextBoxBaseCombo findTextBoxCombo(String name) {
		Widget widget = null;
		
		widget = uiCompanyWidget.getWidget(name);
		
		if (widget instanceof TextBoxBaseCombo) {
			return (TextBoxBaseCombo) widget;
		} else {
			return null;
		}
	}

	@Override
	public void clear() {
		uiCompanyWidget.clear();
	}

	

}
