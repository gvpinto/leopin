package com.leopin.parkfifty.admin.gwt.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gwt.place.shared.PlaceController;
import com.leopin.parkfifty.client.ClientFactory;
import com.leopin.parkfifty.client.activities.HomeActivity;
import com.leopin.parkfifty.client.domain.CompanyProxy;
import com.leopin.parkfifty.client.places.CompanyRegistrationPlace;
import com.leopin.parkfifty.client.places.HomePlace;
import com.leopin.parkfifty.client.views.HomeView;


public class AdminGwtTests {

	HomePlace place;
	ClientFactory clientFactory;
	HomeActivity homeActivity;
	PlaceController placeController;
	HomeView homeView;
	
	@Before
	public void setUp() throws Exception {
		place = new HomePlace();
		clientFactory = mock(ClientFactory.class);
		placeController = mock(PlaceController.class);
		homeView = mock(HomeView.class);
		
		when(clientFactory.getHomeView()).thenReturn(homeView);
		when(clientFactory.getPlaceController()).thenReturn(placeController);
		homeActivity = new HomeActivity(place, clientFactory);
	}
	
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void nextTest() {
		CompanyProxy company = new CompanyProxy();
		company.setName("The First Park Company");
		company.setEmail("thefirstparkcompany@gmail.com");
		company.setUrl("http://www.thefirstparkcompany.com");
		company.setPriPhone("(919) 455-3262");
		company.setFax("(919) 455-3263");
		company.setSecPhone("");
		
		assertTrue(homeActivity.validate("uiName", company.getName()));
		assertTrue(homeActivity.validate("uiUrl", company.getUrl()));
		assertTrue(homeActivity.validate("uiEmail", company.getEmail()));
		assertTrue(homeActivity.validate("uiPriPhone", company.getPriPhone()));
		assertTrue(homeActivity.validate("uiSecPhone", company.getSecPhone()));
		assertTrue(homeActivity.validate("uiFax", company.getFax()));
		
		homeActivity.next(company);
		verify(placeController, times(1)).goTo(any(CompanyRegistrationPlace.class));
	}

}
