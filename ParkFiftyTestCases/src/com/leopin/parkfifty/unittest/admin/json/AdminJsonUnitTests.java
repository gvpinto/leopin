package com.leopin.parkfifty.unittest.admin.json;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leopin.parkfifty.server.controller.AdminController;
import com.leopin.parkfifty.server.service.AdminService;
import com.leopin.parkfifty.shared.domain.Company;

//@RunWith(SpringJUnit4ClassRunner.class)  
//@ContextConfiguration(locations = { "classpath:parkfifty-servlet.xml" }) 
public class AdminJsonUnitTests {

	AdminService service = null;
	MessageSource messages = null;
	Company company = null;
	
	@Before
	public void setUp() throws Exception {
		 service = mock(AdminService.class);
		 messages = mock(MessageSource.class);
		 company = mock(Company.class);
	}


	@Test
	public void testRetrieveCompanyById() {
		
		when(company.getName()).thenReturn("Park Fifty");
		
		when(service.getCompany(anyLong())).thenReturn(company);

		
		AdminController controller = new AdminController(service, messages);
		assertEquals("Park Fifty", controller.getCompany("1").getName());
		verify(service, times(1)).getCompany(anyLong());
		verify(service, never()).getCompany(anyString());
		
	}
	
	@Test
	public void testRetrieveCompanyByName() {

		when(company.getName()).thenReturn("Park Fifty");
		
		when(service.getCompany(anyString())).thenReturn(company);

		
		AdminController controller = new AdminController(service, messages);
		assertEquals("Park Fifty", controller.getCompany("Park Fifty").getName());
		verify(service, never()).getCompany(anyLong());
		verify(service, times(1)).getCompany(anyString());
		
	}
	
	@Test
	public void addCompanyWithoutErrors() {

		when(company.getName()).thenReturn("Company without errors");
		when(company.getEmail()).thenReturn("gpinto@bbandt.com");
		when(company.getUrl()).thenReturn("http://www.bbt.com");
		when(company.getPriPhone()).thenReturn("9194553262");
		
		when(service.getCompany(anyString())).thenReturn(company);

		
		AdminController controller = new AdminController(service, messages);
		assertEquals("Park Fifty", controller.getCompany("Park Fifty").getName());
		verify(service, never()).getCompany(anyLong());
		verify(service, times(1)).getCompany(anyString());
		
	}	
}
