package com.leopin.parkfifty.admin.unit.tests;

//import static org.junit.Assert.*;
//import static org.mockito.Matchers.*;
//import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.validation.ConstraintViolation;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

import com.leopin.parkfifty.server.controller.AdminController;
import com.leopin.parkfifty.server.service.AdminService;
import com.leopin.parkfifty.shared.domain.Company;

public class AdminJsonUnitTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminJsonUnitTests.class);
	AdminService service = null;
	MessageSource messages = null;
	Company company = null;
	DispatcherServlet servlet;

	@Before
	public void setUp() throws Exception {

		service = mock(AdminService.class);
		messages = mock(ResourceBundleMessageSource.class);
		company = mock(Company.class);
	}

	@Test
	public void testRetrieveCompanyById() {

		when(company.getName()).thenReturn("Park Fifty");

		when(service.getCompany(anyLong())).thenReturn(company);

		AdminController controller = new AdminController(this.service, this.messages);
//		ReflectionUtils.setField(ReflectionUtils.findField(AdminController.class, "adminService"), controller, service);
//		ReflectionUtils.setField(ReflectionUtils.findField(AdminController.class, "messages"), controller, messages);
		assertEquals("Park Fifty", controller.getCompany("1").getName());
		verify(service, times(1)).getCompany(anyLong());
		verify(service, never()).getCompany(anyString());

	}

	@Test
	public void testRetrieveCompanyByName() {

		when(company.getName()).thenReturn("Park Fifty");

		when(service.getCompany(anyString())).thenReturn(company);

		AdminController controller = new AdminController(this.service, this.messages);
//		ReflectionUtils.setField(ReflectionUtils.findField(AdminController.class, "adminService"), controller, service);
//		ReflectionUtils.setField(ReflectionUtils.findField(AdminController.class, "messages"), controller, messages);
		assertEquals("Park Fifty", controller.getCompany("Park Fifty")
				.getName());
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
		
		BindingResult bindingResults = mock(BindingResult.class);
		when(bindingResults.hasErrors()).thenReturn(false);
		
		when(company.getId()).thenReturn(1L);
		when(service.addCompany(company)).thenReturn(company);

		AdminController controller = new AdminController(this.service, this.messages);
		assertEquals("Company without errors", controller.addCompany(company, bindingResults).getName());
		
		verify(service, times(1)).addCompany(company);

	}
	
	@Test
	public void testGoodCompanyValidation() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		
		Company company = new Company();
		company.setName("This is a good  company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("9194553262");
		company.setSecPhone("");
		company.setFax("");
		
		
		Set<ConstraintViolation<Company>> result = validator.validate(company);
		LOGGER.error("Result Size[" + result.size() + "]");
		assertEquals(0, result.size());
	}
	
	
	@Test
	public void testCompanyInvalidEmail() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Company company = new Company();
		company.setName("This is a good company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmailcom");
		company.setPriPhone("9194553262");
		company.setSecPhone("");
		company.setFax("");
		
		
		Set<ConstraintViolation<Company>> constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
		assertEquals("Invalid email address.", constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void testCompanyInvalidPhone() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		LOGGER.debug(">>>> " + validator.getClass().getName());
		validator.afterPropertiesSet();
		Company company = new Company();
		company.setName("This is a good company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("9194553262qwe");
		company.setSecPhone("");
		company.setFax("");
		
		
		Set<ConstraintViolation<Company>> constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
		assertEquals("Invalid primary phone number. Can be 9 to 15 digits long.", constraintViolations.iterator().next().getMessage());
	}

	
	@Test
	public void testCompanyInvalidAndShortPhone() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Company company = new Company();
		company.setName("This is a good company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("919455326");
		company.setSecPhone("");
		company.setFax("");
		
		
		Set<ConstraintViolation<Company>> constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
		assertEquals("Invalid primary phone number. Can be 9 to 15 digits long.", constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void testCompanyInvalidSecPhone() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Company company = new Company();
		company.setName("This is a good company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("9194553262");
		company.setSecPhone("a");
		company.setFax("");
		
		
		Set<ConstraintViolation<Company>> constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
		assertEquals("Invalid secondary phone number. Can be 9 to 15 digits long.", constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void testCompanyInvalidFax() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Company company = new Company();
		company.setName("This is a good company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("9194553262");
		company.setSecPhone("");
		company.setFax("a");
		
		
		Set<ConstraintViolation<Company>> constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
		assertEquals("Invalid fax number. Can be 9 to 15 digits long.", constraintViolations.iterator().next().getMessage());
	}

	
	@Test
	public void testCompanyInvalidURL() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Company company = new Company();
		company.setName("This is a good company");
		company.setUrl("https:/goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("9194553262");
		company.setSecPhone("");
		company.setFax("");
		
		
		Set<ConstraintViolation<Company>> constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
		assertEquals("Invalid URL address", constraintViolations.iterator().next().getMessage());
//		for (ConstraintViolation<Company> cv : result) {
//			String path = cv.getPropertyPath().toString();
//
//			if ("name".equals(path) || "url".equals(path) || "email".equals(path) || "priPhone".equals(path) || "secPhone".equals(path) || "fax".equals(path) ) {
//				assertTrue(cv.getConstraintDescriptor().);
//			}
//			else {
//				fail("Invalid constraint violation with path '" + path + "'");
//			}
//		}
	}
	
//	@Test
	public void commandProvidingFormControllerWithCustomEditor() throws Exception {
		
		
		Company company = new Company();
		company.setName("Company without errors");
		company.setEmail("gpinto@bbandt.com");
		company.setUrl("http://www.bbt.com");
		company.setPriPhone("9194553262");

		when(service.getCompany(anyLong())).thenReturn(company);
		
		@SuppressWarnings("serial") DispatcherServlet servlet = new DispatcherServlet() {
			@Override
			protected WebApplicationContext createWebApplicationContext(WebApplicationContext parent) {
				GenericWebApplicationContext wac = new GenericWebApplicationContext();
//				controllerDef.setAutowireMode(RootBeanDefinition.AUTOWIRE_BY_TYPE);
				RootBeanDefinition controllerDef = new RootBeanDefinition(AdminController.class);
				controllerDef.setAutowireCandidate(true);
				controllerDef.getPropertyValues().add("adminService", AdminJsonUnitTests.this.service);
				controllerDef.getPropertyValues().add("messages", AdminJsonUnitTests.this.messages);
				wac.registerBeanDefinition("controller",controllerDef);
//				wac.registerBeanDefinition("viewResolver", new RootBeanDefinition(TestViewResolver.class));		
				
				RootBeanDefinition adapterDef = new RootBeanDefinition(AnnotationMethodHandlerAdapter.class);
				adapterDef.getPropertyValues().add("webBindingInitializer", new MyWebBindingInitializer());
				adapterDef.getPropertyValues().add("messageConverters", new MappingJacksonHttpMessageConverter());
				wac.registerBeanDefinition("handlerAdapter", adapterDef);
				
				RootBeanDefinition mappingDef = new RootBeanDefinition(DefaultAnnotationHandlerMapping.class);
				wac.registerBeanDefinition("handlerMapping", mappingDef);

				wac.refresh();
				return wac;
			}
		};
		servlet.init(new MockServletConfig());

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/admin/company/1");
		request.addHeader("Accept", "application/json");
		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.service(request, response);
		LOGGER.info(response.getContentAsString());
	}
	
	private static class MyWebBindingInitializer implements WebBindingInitializer {

		public void initBinder(WebDataBinder binder, WebRequest request) {
			LocalValidatorFactoryBean vf = new LocalValidatorFactoryBean();
			vf.afterPropertiesSet();
			binder.setValidator(vf);
			assertNotNull(request.getLocale());
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			dateFormat.setLenient(false);
//			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		}
	}

}
