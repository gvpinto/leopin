package com.leopin.parkfifty.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import com.leopin.parkfifty.client.events.AppBusyEvent;
import com.leopin.parkfifty.client.events.AppBusyHandler;
import com.leopin.parkfifty.client.events.AppErrorEvent;
import com.leopin.parkfifty.client.events.AppErrorHandler;
import com.leopin.parkfifty.client.events.AppFreeEvent;
import com.leopin.parkfifty.client.events.AppFreeHandler;
import com.leopin.parkfifty.client.events.AppSuccessEvent;
import com.leopin.parkfifty.client.events.AppSuccessHandler;
import com.leopin.parkfifty.client.places.HomePlace;
import com.leopin.parkfifty.client.resources.AppStyles;
import com.leopin.parkfifty.client.services.ParkFiftyService;
import com.leopin.parkfifty.client.ui.ConfirmationDialog;
import com.leopin.parkfifty.client.ui.ErrorDialog;
import com.leopin.parkfifty.client.ui.SuccessDialog;
import com.leopin.parkfifty.client.views.AppView;

public class ParkFiftyApp {

	EventBus eventBus;
	ClientFactory clientFactory = GWT.create(ClientFactory.class);
//	Label busyLabel = new 
	
	PleaseWaitPopup pleaseWait = new PleaseWaitPopup();
	// Define the JSONP Service Class
	private Place defaultPlace  = new HomePlace();
	
	
	
	public ParkFiftyApp(ParkFiftyService service, EventBus eventBus,
			AppView appView) {

		PlaceController placeController = clientFactory.getPlaceController();

		// Setup the Activity Manager for the Content area
		ActivityMapper contentActivityMapper = new ContentActivityMapper(clientFactory);
		ActivityManager contentActivityManager = new ActivityManager(contentActivityMapper,
				eventBus);
		// AcceptsOneWidget content = appView.getContent();
		contentActivityManager.setDisplay(appView.getContent());


		// Setup the Activity Manager for the Header area
		ActivityMapper headerActivityMapper = new HeaderActivityMapper(clientFactory);
		ActivityManager headerActivityManager = new ActivityManager(headerActivityMapper,
				eventBus);
		headerActivityManager.setDisplay(appView.getHeader());
		
		// Check for Authentication
		
		
		// Start PlaceHistoryManager with our PlaceHistoryMapper
		PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(
				(ParkFiftyHistoryMapper) GWT
						.create(ParkFiftyHistoryMapper.class));
		placeHistoryHandler.register(placeController, eventBus, defaultPlace);

		// Goes to the place represented on URL else default place
		placeHistoryHandler.handleCurrentHistory();

		this.eventBus = eventBus;

		// Bind the events
		bind();

		// Implement a simple "Busy" display that can be shown when application
		// is busy.
//		RootPanel.get().add(busyLabel, 200, 20);
//		busyLabel.setVisible(false);
//		busyLabel.getElement().getStyle().setBackgroundColor("#ff5555");
//		busyLabel.getElement().getStyle().setColor("#ffffff");

	}

	private void bind() {
		// Listen to Busy Events on the Event Bus
		eventBus.addHandler(AppBusyEvent.getType(), new AppBusyHandler() {

			@Override
			public void onAppBusyEvent(AppBusyEvent event) {
//				busyLabel.setVisible(true);
				pleaseWait.center();
			}
		});

		eventBus.addHandler(AppFreeEvent.getType(), new AppFreeHandler() {

			@Override
			public void onAppFreeEvent(AppFreeEvent event) {
//				busyLabel.setVisible(false);
				pleaseWait.hide();

			}
		});

		eventBus.addHandler(AppErrorEvent.getType(), new AppErrorHandler() {

			@Override
			public void onAppErrorEvent(AppErrorEvent event) {
				GWT.log("AppErrorEvent Received. Message: "
						+ event.getMessage());

				// Display the confirmation dialog box with the error message
				ConfirmationDialog dialog = new ErrorDialog(
						clientFactory, event.getPlace());
				dialog.setBodyText(event.getMessage());
				// TODO: Move the message to AppMessages
				dialog.setCaption("Unable to process request");
				dialog.center();
			}

		});
		

		eventBus.addHandler(AppSuccessEvent.getType(), new AppSuccessHandler() {

			@Override
			public void onAppSuccessEvent(AppSuccessEvent event) {
				GWT.log("Event Successful. Message: "
						+ event.getMessage());

				// Display the confirmation dialog box with the error message
				ConfirmationDialog dialog = new SuccessDialog(
						clientFactory, event.getPlace());
				dialog.setBodyText(event.getMessage());
				// TODO: Move the message to AppMessages
				dialog.setCaption("Request successful processed");
				dialog.center();
			}

		});

	}
	
	
	/**
	 * Popup for the help text for validation errors
	 */
	public static class PleaseWaitPopup extends PopupPanel {
		
		public PleaseWaitPopup() {
			super(false);
			SimplePanel wrapper = new SimplePanel();
			wrapper.getElement().setId("preloader");
			setModal(true);
			setGlassEnabled(true);
			Image preloader = new Image(AppStyles.resources().preloader());
			wrapper.add(preloader);
			setWidget(wrapper);
			setStyleName(AppStyles.style().pleaseWait());
		}

	}
	

}
