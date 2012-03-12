package com.leopin.parkfifty.client.views;


public interface HomeView extends View {
	
//	public void showHelpUiName(String text);
//	
//	public void showHelpUiUrl(String text);
//	
//	public void showHelpUiEmail(String text);
//	
//	public void showHelpUiPriPhone(String text);
//	
//	public void showHelpUiSecPhone(String text);
//	
//	public void showHelpUiFax(String text);
	
	public void showHelp(String name);
	
	public void removeHelp(String name);
	
	public void setUiText(String name, String text);

	public void setFocus(String name);
	
}
