package com.leopin.parkfifty.admin.integration.tests;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.server.setup.MockMvcBuilders.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.server.MockMvc;

import com.leopin.parkfifty.config.WebConfig;


//@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//file:webapp/WEB-INF/applicationContext.xml, classpath:cz/aura/cms/servicesApplicationContext.xml"
//@ContextConfiguration(locations = { "classpath:parkfifty-servlet-test.xml", "classpath:parkfifty-service-test.xml"})
//@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={WebConfig.class})
//@ActiveProfiles("dev")
public class AdminIntegrationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminIntegrationTests.class);

	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
//		String contextLoc = "classpath:appContext.xml";
		String warDir = "/Users/gvpinto/Git-Repos/leopin/ParkFifty/war";
		mockMvc = annotationConfigSetup(WebConfig.class)
					.activateProfiles("dev")
					.configureWebAppRootDir(warDir, false)
					.build();
	}

//	@Test
//	public void getCompany() throws Exception {
//
//		handlerAdapter = appCtx.getBean(AnnotationMethodHandlerAdapter.class);
//		request.addHeader("Accept", "application/json");
//		request.setRequestURI("/admin/company/14");
//		request.setMethod("GET");
//		handlerAdapter.handle(request, response,
//				appCtx.getBean(AdminController.class));
//
//		LOGGER.debug(">>>>>>>" + response.getContentAsString());
//
//	}
	
	@Test
	public void testAddNewCompany() throws Exception {
		String output = mockMvc.perform(get("/admin/company/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
//		  .andExpect(status().isOk());
////		  .andExpect(content().type(MediaType.APPLICATION_JSON));
//		.jsonPath(expression, matcher)
		
		System.out.println(">>>" + output);
	
	}

}
