package com.leopin.parkfifty.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

public class AppStyles {
	
	  private static AppResources resources;

	  static {
	    resources = GWT.create(AppResources.class);
	    resources.style().ensureInjected();
	  }

	  public static Style style() {
	    return resources.style();
	  }

	  public static AppResources resources() {
	    return resources;
	  }
	  
	/**
	 * CSS styles that will be used across the application This will help in
	 * custom branding the app
	 * 
	 * @author Glenn Pinto
	 */
	public interface Style extends CssResource {

		String serverResponseLabelError();

		String companyWidget();

		// highlight the background when there is a validation error
		String validateError();

		String xclamation();

		String textBox();

		String floatR();

		String floatL();

		/**
		 * Style for the navigation button
		 * 
		 * @return navbar style
		 */
		String navbar();

		/**
		 * Button style
		 * 
		 * @return button style
		 */
		String button();

		String popup();

		String titleSection();

		String title();

		String labelCol();

		String fieldCol();

		/**
		 * CSS formatting for label cells
		 * 
		 * @return label cell style
		 */
		String labelCell();

		/**
		 * CSS formatting for field cells
		 * 
		 * @return field cell style
		 */
		String fieldCell();

		/**
		 * Column that defines the collection of all the labels and text field
		 * 
		 * @return column style
		 */
		String column();

		/**
		 * Align the dom elements to center
		 * 
		 * @return center style
		 */
		String center();
		
		/**
		 * Define the error message area
		 */
		String errorMsgArea();
		
		/**
		 * Define the characteristics of the error message
		 */
		String errorMsg();
		
		/**
		 * Top Navigation
		 */
		String topNav();
		
		/**
		 * Label color across the application
		 */
		String labelColor();
		
		/**
		 * Style for the textBoxCombo
		 */
		String textBoxCombo();
		
		/**
		 * Style for fields that caused a validation error
		 */
		String error();
		
		/** 
		 * Style for Please Wait Preloader
		 */
		String pleaseWait();
		
	}

	public interface AppResources extends ClientBundle {

	    @NotStrict
		@Source("parkfifty.css")
		public Style style();

		@Source("pfbg.png")
		@ImageOptions(repeatStyle = RepeatStyle.Horizontal)
		public ImageResource pfbg();

		@Source("xclamation_16x16.png")
		public ImageResource xclamation();

		@Source("error_32x32.png")
		public ImageResource errorIcon();

		@Source("checkmark_32x32.png")
		public ImageResource successIcon();
		
		@Source("lock_36x36.png")
		public ImageResource lockIcon();
		
		@Source("preloader.gif")
		public ImageResource preloader();

		// @Source("config.xml")
		// public TextResource initialConfiguration();
		//
		// @Source("manual.pdf")
		// public DataResource ownersManual();
		//
		// @Source("default.txt")
		// public TextResource defaultText();
		//
		// @Source("mycursor.cur")
		// DataResource customCursor();
		//
		// @Source("b.txt")
		// ExternalTextResource asynchronous();
		//
		// @Source("logo.png")
		// ImageResource logo();
		//
		// @Source("arrow.png")
		// @ImageOptions(flipRtl = true)
		// ImageResource pointer();

	}
	
	

}
