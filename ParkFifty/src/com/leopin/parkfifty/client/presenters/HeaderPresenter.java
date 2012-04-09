package com.leopin.parkfifty.client.presenters;

/**
 * This Activity address the top navigation bar
 * @author gvpinto
 *
 */
public interface HeaderPresenter extends Presenter {

	/**
	 * Checking if the client is already authenticated. If so dont'display the sign-up form
	 * @return
	 */
	public boolean isAuthenticated();

	/**
	 * Attempt to login to the system given the Username and Password
	 * @param Username aka UserID or Login ID which is seperate from the email address.
	 * This is Unique across the system
	 * @param Password	password associated with this User ID/Username/Login ID
	 */
	public void attemptToLogin(String username, String password);
	
}
