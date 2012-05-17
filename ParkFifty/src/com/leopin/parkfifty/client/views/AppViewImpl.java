package com.leopin.parkfifty.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.presenters.Presenter;

public class AppViewImpl implements AppView {

//	private static AppViewImplUiBinder uiBinder = GWT
//			.create(AppViewImplUiBinder.class);
	
	SimplePanel uiContent;
	SimplePanel uiHeader;
	SimplePanel uiFooter;
	
	// Is signed on?
	boolean signedOn = false;

//	interface AppViewImplUiBinder extends UiBinder<Widget, AppViewImpl> {
//	}

//	@UiField
	HTMLPanel uiMainPanel;
	
	public AppViewImpl() {
//		uiBinder.createAndBindUi(this);
		
		uiMainPanel = new HTMLPanel("");
		uiMainPanel.getElement().setId("uiWrapper");
		uiHeader = new SimplePanel();
		uiHeader.getElement().setId("uiHeader");
		uiContent = new SimplePanel();
		uiContent.getElement().setId("uiContent");
		uiFooter = new SimplePanel();
		uiFooter.getElement().setId("uiFooter");
		


		uiHeader.add(new HTML("HEADER"));
		uiFooter.add(new HTML("FOOTER"));
		
		uiMainPanel.add(uiHeader);
		uiMainPanel.add(uiContent);
		uiMainPanel.add(uiFooter);
		
		Window.addResizeHandler(resizeHandler);

	}



//	public static AppViewImplUiBinder getUiBinder() {
//		return uiBinder;
//	}



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



	@Override
	public Widget asWidget() {
		return uiMainPanel;
	}

    
}
