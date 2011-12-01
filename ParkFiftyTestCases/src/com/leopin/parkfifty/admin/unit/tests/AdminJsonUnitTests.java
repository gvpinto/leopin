package com.leopin.parkfifty.admin.unit.tests;

//import static org.junit.Assert.*;
//import static org.mockito.Matchers.*;
//import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.junit.Assert;
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
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

import com.leopin.parkfifty.admin.domain.AdminDomain;
import com.leopin.parkfifty.server.controller.AdminController;
import com.leopin.parkfifty.server.service.AdminService;
import com.leopin.parkfifty.shared.domain.Company;
import com.leopin.parkfifty.shared.domain.CompanyUser;
import com.leopin.parkfifty.shared.domain.Location;
import com.leopin.parkfifty.shared.domain.jsonwrapper.NewCompanyWrapper;
import com.leopin.parkfifty.shared.exception.AppException;

public class AdminJsonUnitTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminJsonUnitTests.class);
	AdminService service = null;
	MessageSource messages = null;
	Company company = null;
	CompanyUser companyUser = null;
	DispatcherServlet servlet;

	@Before
	public void setUp() throws Exception {

		service = mock(AdminService.class);
		messages = mock(ResourceBundleMessageSource.class);
		company = mock(Company.class);
		companyUser = mock(CompanyUser.class);
		
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
	public void testForAddCompanyService() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		NewCompanyWrapper newCompanyWrapper = AdminDomain.getNewCompanyWrapper();
		
		when(service.addNewCompany(newCompanyWrapper)).thenReturn(newCompanyWrapper);

		AdminController controller = new AdminController(this.service, this.messages);
		controller.setValidator(validator);
		
		newCompanyWrapper = controller.addCompany(newCompanyWrapper);
		try {
			assertEquals(newCompanyWrapper.getCompany().getName(), newCompanyWrapper.getCompany().getName());
			assertEquals(newCompanyWrapper.getCompanyUser().getFirstName(), newCompanyWrapper.getCompanyUser().getFirstName());
		} catch (AppException e) {
			LOGGER.debug((String)e.getPlaceholderValues()[1]);
		}
		
		verify(service, times(1)).addNewCompany(newCompanyWrapper);

	}
	
	
	@Test
	public void testForAddCompanyUserService() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		companyUser = AdminDomain.getCompanyUser(1L);

		when(service.addCompanyUser(companyUser)).thenReturn(companyUser);

		AdminController controller = new AdminController(this.service, this.messages);
		controller.setValidator(validator);
		try {
			assertEquals(companyUser.getUserId(), controller.addCompanyUser(companyUser).getUserId());	
		} catch (AppException e) {
			LOGGER.debug((String)e.getPlaceholderValues()[1]);
		}
		
		
		verify(service, times(1)).addCompanyUser(companyUser);

	}

	@Test(expected=AppException.class)
	public void testAddCompanyWithErrors() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		
		NewCompanyWrapper newCompanyWrapper = AdminDomain.getNewCompanyWrapper();
		newCompanyWrapper.getCompany().setEmail("gpintobbant.com");

		when(service.addNewCompany(newCompanyWrapper)).thenReturn(newCompanyWrapper);

		AdminController controller = new AdminController(this.service, this.messages);
		controller.setValidator(validator);
		assertEquals(newCompanyWrapper.getCompany().getName(), controller.addCompany(newCompanyWrapper).getCompany().getName());
		
		verify(service, times(0)).addNewCompany(newCompanyWrapper);

	}

	@Test
	public void testGoodCompanyValidation() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		
		Company company = new Company();
		company.setCode("THIS1SGOOD");
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
		company.setCode("THIS1SGOOD");
		company.setName("This is a good company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmailcom");
		company.setPriPhone("9194553262");
		company.setSecPhone("");
		company.setFax("");
		
		
		Set<ConstraintViolation<Company>> constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
		
		company.setEmail(null);
		constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());		
//		assertEquals("Invalid email address.", constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void testCompanyInvalidPriPhone() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Company company = new Company();
		company.setCode("THIS1SGOOD");
		company.setName("This is a good company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("919455326aqwe");
		company.setSecPhone("");
		company.setFax("");
		
		
		Set<ConstraintViolation<Company>> constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
		
		company.setPriPhone(null);
		constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());		

		//		assertEquals("Invalid primary phone number. Can be 9 to 15 digits long.", constraintViolations.iterator().next().getMessage());
	}

	
	@Test
	public void testCompanyInvalidAndShortPhone() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Company company = new Company();
		company.setCode("THIS1SGOOD");
		company.setName("This is a good company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("919455326");
		company.setSecPhone("");
		company.setFax("");
		
		
		Set<ConstraintViolation<Company>> constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
		assertEquals("Invalid primary phone number. Should be 9 to 15 digits long.", constraintViolations.iterator().next().getMessage());
		
		
		company.setPriPhone(null);
		constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testCompanyInvalidSecPhone() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Company company = new Company();
		company.setCode("THIS1SGOOD");
		company.setName("This is a good company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("9194553262");
		company.setSecPhone("a");
		company.setFax("");
		
		
		Set<ConstraintViolation<Company>> constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
//		assertEquals("Invalid secondary phone number. Can be 9 to 15 digits long.", constraintViolations.iterator().next().getMessage());
		
		company.setSecPhone(null);
		constraintViolations = validator.validate(company);
		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testCompanyInvalidFax() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Company company = new Company();
		company.setCode("THIS1SGOOD");
		company.setName("This is a good company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("9194553262");
		company.setSecPhone("");
		company.setFax("a");
		
		
		Set<ConstraintViolation<Company>> constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
//		assertEquals("Invalid fax number. Can be 9 to 15 digits long.", constraintViolations.iterator().next().getMessage());
		
		company.setFax(null);
		constraintViolations = validator.validate(company);
		assertEquals(0, constraintViolations.size());
	}

	
	@Test
	public void testCompanyInvalidURL() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Company company = new Company();
		company.setCode("THIS1SGOOD");
		company.setName("This is a good company");
		company.setUrl("https:/goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("9194553262");
		company.setSecPhone("");
		company.setFax("");
		
		
		Set<ConstraintViolation<Company>> constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
		
		company.setUrl(null);
		constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
//		assertEquals("Invalid URL address", constraintViolations.iterator().next().getMessage());
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

	@Test
	public void testLocationValid() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Location location = AdminDomain.getLocation();
		
		Set<ConstraintViolation<Location>> constraintViolations = validator.validate(location);
		printConstraintViolations(constraintViolations);
		assertEquals(0, constraintViolations.size());		
	}
	
	@Test
	public void testCompanyUserValid() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		CompanyUser companyUser = AdminDomain.getCompanyUser(1L);
		Set<ConstraintViolation<CompanyUser>> constraintViolations = validator.validate(companyUser);
		printConstraintViolations(constraintViolations);
		assertEquals(0, constraintViolations.size());		
	}
	
	
	@Test
	public void canDeserializeCompany() {
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = TypeFactory.defaultInstance().constructType(Company.class);
		Assert.assertTrue(mapper.canDeserialize(javaType));
	}
	
	@Test
	public void canDeserializeLocation() {
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = TypeFactory.defaultInstance().constructType(Location.class);
		Assert.assertTrue(mapper.canDeserialize(javaType));
	}
	
	@Test
	public void canDeserializeCompanyUser() {
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = TypeFactory.defaultInstance().constructType(CompanyUser.class);
		Assert.assertTrue(mapper.canDeserialize(javaType));
	}
	private <T> void printConstraintViolations(
			Set<ConstraintViolation<T>> constraintViolations) {
		
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<T> constraintViolation : constraintViolations) {
				LOGGER.debug(">> " + constraintViolation.getMessage());
			}
		}
		
	}

}
