package com.leopin.parkfifty.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

public interface ParkFiftyResources extends ClientBundle {
	
	  public static final ParkFiftyResources INSTANCE =  GWT.create(ParkFiftyResources.class);

	  interface Style extends CssResource {
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
		   * @return navbar style
		   */
		  String navbar();
		  /**
		   * Button style
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
		   * @return label cell style
		   */
		  String labelCell();
		  
		  /**
		   * CSS formatting for field cells
		   * @return field cell style
		   */
		  String fieldCell();
		  /**
		   * Column that defines the collection of all the labels and text field
		   * @return column style
		   */
		  String column();
		  
		  /**
		   * Align the dom elements to center
		   * @return center style
		   */
		  String center();
	  }
	  
	  @Source("parkfifty.css")
	  public Style style();
	  
	  @Source("pfbg.png")
	  @ImageOptions(repeatStyle=RepeatStyle.Horizontal)
	  public ImageResource pfbg();
	  
	  @Source("xclamation.png")
	  public ImageResource xclamation();

//	  @Source("config.xml")
//	  public TextResource initialConfiguration();
//
//	  @Source("manual.pdf")
//	  public DataResource ownersManual();
//	  
//	  @Source("default.txt")
//	  public TextResource defaultText();
//	  
//	  @Source("mycursor.cur")
//	  DataResource customCursor();
//	  
//	  @Source("b.txt")
//	  ExternalTextResource asynchronous();
//	  
//	  @Source("logo.png")
//	  ImageResource logo();
//
//	  @Source("arrow.png")
//	  @ImageOptions(flipRtl = true)
//	  ImageResource pointer();
	  
}
