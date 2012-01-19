package com.leopin.parkfifty.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.leopin.parkfifty.client.ui.CompanyWidget;

public class MainView extends Composite implements HasKeyPressHandlers {

	private static MainViewUiBinder uiBinder = GWT
			.create(MainViewUiBinder.class);
	
	SimplePanel content = new SimplePanel();
	SimplePanel header = new SimplePanel();
	SimplePanel footer = new SimplePanel();

	interface MainViewUiBinder extends UiBinder<Widget, MainView> {
	}

	@UiField
	HTMLPanel uiMainPanel;
	
	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
		Window.addResizeHandler(resizeHandler);
		uiMainPanel.add(header, "header");
		uiMainPanel.add(content, "content");
		uiMainPanel.add(footer, "footer");
		CompanyWidget companyWidget = new CompanyWidget();
		this.addKeyPressHandler(companyWidget);
		FlowPanel companyPanel = new FlowPanel();
		companyPanel.add(companyWidget);
		content.add(companyPanel);
	}



	public static MainViewUiBinder getUiBinder() {
		return uiBinder;
	}



	public SimplePanel getContent() {
		return content;
	}



	public SimplePanel getHeader() {
		return header;
	}



	public SimplePanel getFooter() {
		return footer;
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
        setWidth("100%");
        setHeight("100%");
    }

	@Override
	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return addDomHandler(handler, KeyPressEvent.getType());
	}

    
}
