package com.leopin.parkfifty.client.views;

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
import com.leopin.parkfifty.client.resources.ParkFiftyResources;
import com.leopin.parkfifty.client.resources.ParkFiftyResources.Style;
import com.leopin.parkfifty.client.ui.CompanyWidget;
import com.leopin.parkfifty.client.ui.TextBoxCombo;

public class HomeViewImpl extends Composite implements HomeView, FocusHandler,
		BlurHandler, KeyPressHandler, HasKeyPressHandlers {

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
		this.addKeyPressHandler(this);
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
		this.presenter.onFocus(name, text);
		
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
		
		if (name.matches("uiPriPhone")) {
			uiCompanyWidget.getUiPriPhone().setText(text);
		} else if (name.matches("uiSecPhone")) {
			uiCompanyWidget.getUiSecPhone().setText(text);
		} else if (name.matches("uiFax")) {
			uiCompanyWidget.getUiFax().setText(text);
		}
		
	}

	/**
	 * Remove the help and any highlighting associated with the help
	 */
	public void removeHelp(String name) {
		
		TextBoxCombo textBoxCombo = findTextBoxCombo(name);

		if (textBoxCombo != null) {
			textBoxCombo.getUiTextBox().removeStyleName(ParkFiftyResources.INSTANCE.style()
					.validateError());
			textBoxCombo.hideHelp();
		}

	}
	
	/**
	 * Show help related artifacts and highlighting to indicate an error
	 */
	public void showHelp(String name) {
		TextBoxCombo textBox = findTextBoxCombo(name);
		if (textBox != null) {
			textBox.showHelp();
		}	
	}
	@Override
	public void setFocus(String name) {
		Widget widget = findTextBoxCombo(name);
		if (widget != null && widget instanceof TextBoxCombo) {
			((TextBoxCombo) widget).getUiTextBox().setFocus(true);
		}
//		uiCompanyWidget.getUiName().getUiTextBox().setFocus(true);
	}

	@Override
	public Style style() {
		return ParkFiftyResources.INSTANCE.style();
	}

	/*
	 * PRIVATE METHODS
	 */
	private void next() {
		this.presenter.next(uiCompanyWidget.getCompany());
	}

	/**
	 * Search for the right TextBoxCombo depending on the name 
	 * @param name Name of the widget for which the widget object has to be retrieved
	 * @return TextBoxCombo object
	 */
	private TextBoxCombo findTextBoxCombo(String name) {
		Widget widget = null;
		
		widget = uiCompanyWidget.getWidget(name);
		
		if (widget instanceof TextBoxCombo) {
			return (TextBoxCombo) widget;
		} else {
			return null;
		}
	}
	

}
