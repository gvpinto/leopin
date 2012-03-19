package com.leopin.parkfifty.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.presenters.Presenter;

public class AppViewImpl extends SimplePanel implements AppView {

	private static AppViewImplUiBinder uiBinder = GWT
			.create(AppViewImplUiBinder.class);
	
	SimplePanel uiContent = new SimplePanel();
	SimplePanel uiHeader = new SimplePanel();
	SimplePanel uiFooter = new SimplePanel();
	
	// Is signed on?
	boolean signedOn = false;

	interface AppViewImplUiBinder extends UiBinder<Widget, AppViewImpl> {
	}

	@UiField
	HTMLPanel uiMainPanel;
	
	public AppViewImpl() {
		add(uiBinder.createAndBindUi(this));
		Window.addResizeHandler(resizeHandler);
		uiHeader.add(new HTML("HEADER"));
		uiFooter.add(new HTML("FOOTER"));
		uiMainPanel.add(uiHeader, "uiHeader");
		uiMainPanel.add(uiContent, "uiContent");
		uiMainPanel.add(uiFooter, "uiFooter");
	}



	public static AppViewImplUiBinder getUiBinder() {
		return uiBinder;
	}



	public SimplePanel getContent() {
		return uiContent;
	}



	public SimplePanel getHeader() {
		return uiHeader;
	}



	public SimplePanel getFooter() {
		return uiFooter;
	}



	public HTMLPanel getUiMainPanel() {
		return uiMainPanel;
	}
//	public MainView(String firstName) {
//		initWidget(uiBinder.createAndBindUi(this));
//	}

    /* *************  WIDGET CENTERING CODE *************** */
    private ResizeHandler resizeHandler = new ResizeHandler()
    {
        public void onResize (ResizeEvent event)
        {
            setWidgetToMaxWidthAndHeight();
        }
    };



    private void setWidgetToMaxWidthAndHeight ()
    {
//    	uiHeader.setHeight("42px");
//    	uiContent.setHeight("550px");
//    	uiFooter.setHeight("8px");
//        setWidth("100%");
//        setHeight("100%");
    }

//	@Override
//	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
//		return addDomHandler(handler, KeyPressEvent.getType());
//	}



	@Override
	public void setPresenter(Presenter presenter) {
		
	}

    
}
