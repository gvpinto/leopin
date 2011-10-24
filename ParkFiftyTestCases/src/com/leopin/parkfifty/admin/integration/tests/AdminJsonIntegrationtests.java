package com.leopin.parkfifty.admin.integration.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.leopin.parkfifty.server.controller.AdminController;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
//file:webapp/WEB-INF/applicationContext.xml, classpath:cz/aura/cms/servicesApplicationContext.xml"
@ContextConfiguration(locations = { "classpath:parkfifty-servlet-test.xml", "classpath:parkfifty-service-test.xml"})
public class AdminJsonIntegrationtests {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminJsonIntegrationtests.class);

	@Autowired
	private ApplicationContext appCtx;

	MockHttpServletRequest request;
	MockHttpServletResponse response;
	HandlerAdapter handlerAdapter;
	
	@Before
	public void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void getCompany() throws Exception {

		handlerAdapter = appCtx.getBean(AnnotationMethodHandlerAdapter.class);
		request.addHeader("Accept", "application/json");
		request.setRequestURI("/admin/company/14");
		request.setMethod("GET");
		handlerAdapter.handle(request, response,
				appCtx.getBean(AdminController.class));

		LOGGER.debug(">>>>>>>" + response.getContentAsString());

	}

}
