package com.leopin.parkfifty.client.views;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.presenters.HeaderPresenter;
import com.leopin.parkfifty.client.presenters.Presenter;
import com.leopin.parkfifty.client.resources.ParkFiftyResources;
import com.leopin.parkfifty.client.resources.ParkFiftyResources.Style;
import com.leopin.parkfifty.client.ui.LoginDialog;
import com.leopin.parkfifty.client.ui.TopNav;

/**
 * Defined a view to be the top nav. This is not defined as a composite
 * and avoid an unnecessary <DIV>
 * @author Glenn Pinto
 *
 */
public class HeaderViewImpl  implements HeaderView, ClickHandler {

	HeaderPresenter presenter;
	TopNav topNav;
	LoginDialog loginDialog;
	
	public HeaderViewImpl() {
		loginDialog = new LoginDialog();
		loginDialog.setVisible(false);
		topNav = new TopNav();
//		topNav.addClickHandler(this);
		topNav.getUiLogin().addClickHandler(this);
		loginDialog.getUiLogin().addClickHandler(this);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = (HeaderPresenter) presenter;

		/* 
		 * When the loading the home page check if the user is already authenticated.
		 * If Authenticated then show the Log Off link
		 *  If Not Authenticated the display the Log In Link and also the sign up fields
		 */
		if (this.presenter.isAuthenticated()) {
			topNav.setToLogOff();
		} else {
			topNav.setToLogin();
		}
	}

	@Override
	public Widget asWidget() {
		return topNav;
	}

	@Override
	public Style style() {
		return ParkFiftyResources.INSTANCE.style();
	}

	@Override
	public void onClick(ClickEvent event) {
		Object source = event.getSource();
		
		if (source instanceof Anchor) {
			Anchor uiLoginAnchor = (Anchor) source;
		} else if(source instanceof Button) {
			Button uiLoginButton = (Button) source;
			if ("Login".equals(uiLoginButton.getText())) {
				attemptToLogin();
			}
		}
		
		loginDialog.clear();
		loginDialog.center(); // Need to extend Popup
	}

	// -- PRIVATE METHODS --
	private void attemptToLogin() {

		this.presenter.attemptToLogin(
				loginDialog.getUiUsername().getText(), 
				loginDialog.getUiPassword().getText());
		
	}

}
