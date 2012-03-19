package com.leopin.parkfifty.client;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gwt.place.shared.PlaceController;
import com.leopin.parkfifty.admin.domain.AdminDomainData;
import com.leopin.parkfifty.client.ClientFactory;
import com.leopin.parkfifty.client.activities.CompanyRegistrationActivity;
import com.leopin.parkfifty.client.activities.HomeActivity;
import com.leopin.parkfifty.client.domain.CompanyProxyImpl;
import com.leopin.parkfifty.client.places.CompanyRegistrationPlace;
import com.leopin.parkfifty.client.places.HomePlace;
import com.leopin.parkfifty.client.views.HomeView;
import com.leopin.parkfifty.shared.domain.CompanyProxy;
import com.leopin.parkfifty.shared.domain.CompanyUserProxy;

@RunWith(MockitoJUnitRunner.class)
public class AdminGwtUnitTests {


	HomePlace place;
	
	@Mock
	CompanyRegistrationActivity companyRegistrationActivity;

	@Mock
	ClientFactory clientFactory;
	@Mock
	HomeActivity homeActivity;
	
	@Mock
	PlaceController placeController;
	@Mock
	HomeView homeView;
	
	@Before
	public void setUp() throws Exception {
		place = new HomePlace();
//		clientFactory = mock(ClientFactory.class);
//		placeController = mock(PlaceController.class);
//		homeView = mock(HomeView.class);
		
		when(clientFactory.getHomeView()).thenReturn(homeView);
		when(clientFactory.getPlaceController()).thenReturn(placeController);

		homeActivity = new HomeActivity(place, clientFactory);
	}
	
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void nextTestPass() {
		CompanyProxy company = new CompanyProxyImpl();
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
	
	@Test
	public void nextTestPass2() {
		CompanyProxy company = new CompanyProxyImpl();
		company.setName("The First Park Company");
		company.setEmail("thefirstparkcompany@gmail.com");
		company.setUrl("www.thefirstparkcompany.com");
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
	
	
	@Test
	public void nextTestUrlFail() {
		CompanyProxy company = new CompanyProxyImpl();
		company.setName("The First Park Company");
		company.setEmail("thefirstparkcompany@gmail.com");
		company.setUrl("thefirstparkcompany");
		company.setPriPhone("(919) 455-3262");
		company.setFax("(919) 455-3263");
		company.setSecPhone("");
		
		assertTrue(homeActivity.validate("uiName", company.getName()));
		assertFalse(homeActivity.validate("uiUrl", company.getUrl()));
		assertTrue(homeActivity.validate("uiEmail", company.getEmail()));
		assertTrue(homeActivity.validate("uiPriPhone", company.getPriPhone()));
		assertTrue(homeActivity.validate("uiSecPhone", company.getSecPhone()));
		assertTrue(homeActivity.validate("uiFax", company.getFax()));
		
		String name = homeActivity.next(company);
		verify(placeController, times(0)).goTo(any(CompanyRegistrationPlace.class));
//		verify(homeView, times(1)).setFocus(anyString());
		assertEquals("uiUrl", name);
	}

	@Test
	public void nextTestPriPhoneFail() {
		CompanyProxy company = new CompanyProxyImpl();
		company.setName("The First Park Company");
		company.setEmail("thefirstparkcompany@gmail.com");
		company.setUrl("thefirstparkcompany.parkfifty.com");
		company.setPriPhone("(919) 455-326");
		company.setFax("(919) 455-3263");
		company.setSecPhone("");
		
		assertTrue(homeActivity.validate("uiName", company.getName()));
		assertTrue(homeActivity.validate("uiUrl", company.getUrl()));
		assertTrue(homeActivity.validate("uiEmail", company.getEmail()));
		assertFalse(homeActivity.validate("uiPriPhone", company.getPriPhone()));
		assertTrue(homeActivity.validate("uiSecPhone", company.getSecPhone()));
		assertTrue(homeActivity.validate("uiFax", company.getFax()));
		
		String name = homeActivity.next(company);
		verify(placeController, times(0)).goTo(any(CompanyRegistrationPlace.class));
//		verify(homeView, times(1)).setFocus(anyString());
		assertEquals("uiPriPhone", name);
	}
	
	@Test
	public void testcompanyAndUserJsonFeed() {
		when(companyRegistrationActivity.registerCompany(isA(CompanyProxy.class), isA(CompanyUserProxy.class))).thenCallRealMethod();
		System.out.println(companyRegistrationActivity.registerCompany(AdminDomainData.getCompanyProxy(), AdminDomainData.getCompanyUserProxy()));
		
	}
	
	
	

}
