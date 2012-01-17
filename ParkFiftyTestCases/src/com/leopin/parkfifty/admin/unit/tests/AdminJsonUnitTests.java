package com.leopin.parkfifty.admin.unit.tests;

//import static org.junit.Assert.*;
//import static org.mockito.Matchers.*;
//import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

import com.ashriv.security.server.Role;
import com.leopin.parkfifty.admin.domain.AdminDomainData;
import com.leopin.parkfifty.server.controllers.AdminController;
import com.leopin.parkfifty.server.services.AdminService;
import com.leopin.parkfifty.shared.domain.Company;
import com.leopin.parkfifty.shared.domain.CompanyAndUser;
import com.leopin.parkfifty.shared.domain.CompanyUser;
import com.leopin.parkfifty.shared.domain.Location;
import com.leopin.parkfifty.shared.exception.AppException;

public class AdminJsonUnitTests {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AdminJsonUnitTests.class);
	AdminService service = null;
	MessageSource messages = null;
	Company company = null;
	CompanyUser companyUser = null;
	DispatcherServlet servlet;

	@Before
	public void setUp() throws Exception {
//		ShaPasswordEncoder
//		DaoAuthenticationProvider
//		Md5PasswordEncoder
//		SystemWideSaltSource
//		User
//		ReflectionSaltSource

		
		service = mock(AdminService.class);
		messages = mock(ResourceBundleMessageSource.class);
		company = mock(Company.class);
		companyUser = mock(CompanyUser.class);

	}

	@Test
	public void testRetrieveCompanyById() {

		when(company.getName()).thenReturn("Park Fifty");

		when(service.getCompany(anyLong())).thenReturn(company);

		AdminController controller = new AdminController(this.service,
				this.messages);
		// ReflectionUtils.setField(ReflectionUtils.findField(AdminController.class,
		// "adminService"), controller, service);
		// ReflectionUtils.setField(ReflectionUtils.findField(AdminController.class,
		// "messages"), controller, messages);
		assertEquals("Park Fifty", controller.getCompany("1").getName());
		verify(service, times(1)).getCompany(anyLong());
		verify(service, never()).getCompany(anyString());

	}

	@Test
	public void testRetrieveCompanyByName() {

		when(company.getName()).thenReturn("Park Fifty");

		when(service.getCompany(anyString())).thenReturn(company);

		AdminController controller = new AdminController(this.service,
				this.messages);
		// ReflectionUtils.setField(ReflectionUtils.findField(AdminController.class,
		// "adminService"), controller, service);
		// ReflectionUtils.setField(ReflectionUtils.findField(AdminController.class,
		// "messages"), controller, messages);
		assertEquals("Park Fifty", controller.getCompany("Park Fifty")
				.getName());
		verify(service, never()).getCompany(anyLong());
		verify(service, times(1)).getCompany(anyString());

	}

	@Test
	public void testForAddCompanyService() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		CompanyAndUser newCompanyWrapper = AdminDomainData
				.getCompanyAndUser();

		when(service.addNewCompany(newCompanyWrapper)).thenReturn(
				newCompanyWrapper);

		AdminController controller = new AdminController(this.service,
				this.messages);
		controller.setValidator(validator);

		newCompanyWrapper = controller.addCompany(newCompanyWrapper);
		try {
			assertEquals(newCompanyWrapper.getCompany().getName(),
					newCompanyWrapper.getCompany().getName());
			assertEquals(newCompanyWrapper.getCompanyUser().getFirstName(),
					newCompanyWrapper.getCompanyUser().getFirstName());
		} catch (AppException e) {
			LOGGER.debug((String) e.getPlaceholderValues()[1]);
		}

		verify(service, times(1)).addNewCompany(newCompanyWrapper);

	}

	@Test
	public void testForAddCompanyUserService() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		companyUser = AdminDomainData.getCompanyUser(1L);

		when(service.addCompanyUser((CompanyUser) anyObject())).thenReturn(
				companyUser);

		AdminController controller = new AdminController(this.service,
				this.messages);
		controller.setValidator(validator);
		try {
			assertEquals(companyUser.getUsername(),
					controller.addCompanyUser(companyUser).getUsername());
		} catch (AppException e) {
			LOGGER.debug((String) e.getPlaceholderValues()[1]);
		}

		verify(service, times(1)).addCompanyUser((CompanyUser) anyObject());

	}

	@Test(expected = AppException.class)
	public void testAddCompanyWithErrors() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();

		CompanyAndUser newCompanyWrapper = AdminDomainData
				.getCompanyAndUser();
		newCompanyWrapper.getCompany().setEmail("gpintobbant.com");

		when(service.addNewCompany(newCompanyWrapper)).thenReturn(
				newCompanyWrapper);

		AdminController controller = new AdminController(this.service,
				this.messages);
		controller.setValidator(validator);
		assertEquals(newCompanyWrapper.getCompany().getName(), controller
				.addCompany(newCompanyWrapper).getCompany().getName());

		verify(service, times(0)).addNewCompany(newCompanyWrapper);

	}

	@Test
	public void testGoodCompanyValidation() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();

		Company company = new Company();
//		company.setCode("THIS1SGOOD");
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
//		company.setCode("THIS1SGOOD");
		company.setName("This is a good company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmailcom");
		company.setPriPhone("9194553262");
		company.setSecPhone("");
		company.setFax("");

		Set<ConstraintViolation<Company>> constraintViolations = validator
				.validate(company);
		assertEquals(1, constraintViolations.size());

		company.setEmail(null);
		constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
		// assertEquals("Invalid email address.",
		// constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void testCompanyInvalidPriPhone() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Company company = new Company();
//		company.setCode("THIS1SGOOD");
		company.setName("This is a good company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("919455326aqwe");
		company.setSecPhone("");
		company.setFax("");

		Set<ConstraintViolation<Company>> constraintViolations = validator
				.validate(company);
		assertEquals(1, constraintViolations.size());

		company.setPriPhone(null);
		constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());

		// assertEquals("Invalid primary phone number. Can be 9 to 15 digits long.",
		// constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void testCompanyInvalidAndShortPhone() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Company company = new Company();
//		company.setCode("THIS1SGOOD");
		company.setName("This is a good company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("919455326");
		company.setSecPhone("");
		company.setFax("");

		Set<ConstraintViolation<Company>> constraintViolations = validator
				.validate(company);
		assertEquals(1, constraintViolations.size());
		assertEquals(
				"Invalid primary phone number. Should be 9 to 15 digits long.",
				constraintViolations.iterator().next().getMessage());

		company.setPriPhone(null);
		constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testCompanyInvalidSecPhone() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Company company = new Company();
//		company.setCode("THIS1SGOOD");
		company.setName("This is a good company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("9194553262");
		company.setSecPhone("a");
		company.setFax("");

		Set<ConstraintViolation<Company>> constraintViolations = validator
				.validate(company);
		assertEquals(1, constraintViolations.size());
		// assertEquals("Invalid secondary phone number. Can be 9 to 15 digits long.",
		// constraintViolations.iterator().next().getMessage());

		company.setSecPhone(null);
		constraintViolations = validator.validate(company);
		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testCompanyInvalidFax() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Company company = new Company();
//		company.setCode("THIS1SGOOD");
		company.setName("This is a good company");
		company.setUrl("https://www.goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("9194553262");
		company.setSecPhone("");
		company.setFax("a");

		Set<ConstraintViolation<Company>> constraintViolations = validator
				.validate(company);
		assertEquals(1, constraintViolations.size());
		// assertEquals("Invalid fax number. Can be 9 to 15 digits long.",
		// constraintViolations.iterator().next().getMessage());

		company.setFax(null);
		constraintViolations = validator.validate(company);
		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testCompanyInvalidURL() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Company company = new Company();
//		company.setCode("THIS1SGOOD");
		company.setName("This is a good company");
		company.setUrl("https:/goodcompany.com");
		company.setEmail("goodcompany@gmail.com");
		company.setPriPhone("9194553262");
		company.setSecPhone("");
		company.setFax("");

		Set<ConstraintViolation<Company>> constraintViolations = validator
				.validate(company);
		assertEquals(1, constraintViolations.size());

		company.setUrl(null);
		constraintViolations = validator.validate(company);
		assertEquals(1, constraintViolations.size());
		// assertEquals("Invalid URL address",
		// constraintViolations.iterator().next().getMessage());
		// for (ConstraintViolation<Company> cv : result) {
		// String path = cv.getPropertyPath().toString();
		//
		// if ("name".equals(path) || "url".equals(path) || "email".equals(path)
		// || "priPhone".equals(path) || "secPhone".equals(path) ||
		// "fax".equals(path) ) {
		// assertTrue(cv.getConstraintDescriptor().);
		// }
		// else {
		// fail("Invalid constraint violation with path '" + path + "'");
		// }
		// }
	}

	// @Test
	public void commandProvidingFormControllerWithCustomEditor()
			throws Exception {

		Company company = new Company();
		company.setName("Company without errors");
		company.setEmail("gpinto@bbandt.com");
		company.setUrl("http://www.bbt.com");
		company.setPriPhone("9194553262");

		when(service.getCompany(anyLong())).thenReturn(company);

		@SuppressWarnings("serial")
		DispatcherServlet servlet = new DispatcherServlet() {
			@Override
			protected WebApplicationContext createWebApplicationContext(
					WebApplicationContext parent) {
				GenericWebApplicationContext wac = new GenericWebApplicationContext();
				// controllerDef.setAutowireMode(RootBeanDefinition.AUTOWIRE_BY_TYPE);
				RootBeanDefinition controllerDef = new RootBeanDefinition(
						AdminController.class);
				controllerDef.setAutowireCandidate(true);
				controllerDef.getPropertyValues().add("adminService",
						AdminJsonUnitTests.this.service);
				controllerDef.getPropertyValues().add("messages",
						AdminJsonUnitTests.this.messages);
				wac.registerBeanDefinition("controller", controllerDef);
				// wac.registerBeanDefinition("viewResolver", new
				// RootBeanDefinition(TestViewResolver.class));

				RootBeanDefinition adapterDef = new RootBeanDefinition(
						AnnotationMethodHandlerAdapter.class);
				adapterDef.getPropertyValues().add("webBindingInitializer",
						new MyWebBindingInitializer());
				adapterDef.getPropertyValues().add("messageConverters",
						new MappingJacksonHttpMessageConverter());
				wac.registerBeanDefinition("handlerAdapter", adapterDef);

				RootBeanDefinition mappingDef = new RootBeanDefinition(
						DefaultAnnotationHandlerMapping.class);
				wac.registerBeanDefinition("handlerMapping", mappingDef);

				wac.refresh();
				return wac;
			}
		};
		servlet.init(new MockServletConfig());

		MockHttpServletRequest request = new MockHttpServletRequest("GET",
				"/admin/company/1");
		request.addHeader("Accept", "application/json");
		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.service(request, response);
		LOGGER.info(response.getContentAsString());
	}

	private static class MyWebBindingInitializer implements
			WebBindingInitializer {

		public void initBinder(WebDataBinder binder, WebRequest request) {
			LocalValidatorFactoryBean vf = new LocalValidatorFactoryBean();
			vf.afterPropertiesSet();
			binder.setValidator(vf);
			assertNotNull(request.getLocale());
			// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// dateFormat.setLenient(false);
			// binder.registerCustomEditor(Date.class, new
			// CustomDateEditor(dateFormat, false));
		}
	}

	@Test
	public void testLocationValid() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		Location location = AdminDomainData.getLocation();

		Set<ConstraintViolation<Location>> constraintViolations = validator
				.validate(location);
		printConstraintViolations(constraintViolations);
		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testCompanyUserValid() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		CompanyUser companyUser = AdminDomainData.getCompanyUser(1L);
		Set<ConstraintViolation<CompanyUser>> constraintViolations = validator
				.validate(companyUser);
		printConstraintViolations(constraintViolations);
		assertEquals(0, constraintViolations.size());
	}

	/**
	 * testing for serializing and deserializing from a Jackson standpoint
	 */

	@Test
	public void testSerializeCompany() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Company company = AdminDomainData.getCompany();
			Assert.assertTrue(mapper.canSerialize(Company.class));
			// LOGGER.info(mapper.writeValueAsString(company));
			String actualValue = mapper.writeValueAsString(company);
			System.out.println(actualValue);
			assertTrue(actualValue.indexOf(company.getName()) > 0);
			assertTrue(actualValue.indexOf(company.getEmail()) > 0);
			assertTrue(actualValue.indexOf(company.getPriPhone()) > 0);
			assertTrue(actualValue.indexOf(company.getUpdateUid()) > 0);

		} catch (Exception e) {
			Assert.fail("Exception occurred: " + e.getMessage());
		}
	}

	@Test
	public void testDeserializeCompany() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JavaType companyType = TypeFactory.defaultInstance().constructType(
					Company.class);
			Assert.assertTrue(mapper.canDeserialize(companyType));
			String content = "{\"name\":\"This is a Good Company 9943\",\"id\":null,\"email\":\"gpinto@bbandt.com\",\"priPhone\":\"9194553262\",\"url\":\"http://www.ashriv.com\",\"fax\":\"9194470110\",\"updateTs\":1325451862628,\"secPhone\":\"\",\"normName\":\"this is a good company 9943\",\"updateUid\":\"gvpinto\"}";
			Company company = mapper.readValue(content, companyType);
			assertEquals("This is a Good Company 9943", company.getName());
			assertEquals("http://www.ashriv.com", company.getUrl());
			assertEquals("gpinto@bbandt.com", company.getEmail());
			assertEquals("9194553262", company.getPriPhone());
			assertEquals("9194470110", company.getFax());

		} catch (Exception e) {
			Assert.fail("canDeserializeCompany failed: " + e.getMessage());
		}
	}

	@Test
	public void testSerializeCompanyUser() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			CompanyUser companyUser = AdminDomainData.getCompanyUser(1L);
			Assert.assertTrue(mapper.canSerialize(CompanyUser.class));
			// LOGGER.info(mapper.writeValueAsString(companyUser));
			// String expectedValue = "{\"id\":null,\"timestamp\":" +
			// companyUser.getTimestamp().getTime() +
			// ",\"suffix\":\"III\",\"password\":\"M1ng1L4r2\",\"firstName\":\"Glenn\",\"userId\":\""+
			// companyUser.getUserId()
			// +"\",\"email\":\"gvpinto@gmail.com\",\"priPhone\":\"9194553262\",\"fax\":\"\",\"active\":true,\"secPhone\":\"9194553263\",\"role\":\"OWNER\",\"title\":\"Mr.\",\"lastName\":\"Pinto\",\"middleInitial\":\"J\",\"entitlements\":[\"NONE\",\"ADD_USER\"],\"approved\":true,\"companyId\":1}";
			// String expectedValue = "{\"id\":null,\"timestamp\":" +
			// companyUser.getTimestamp().getTime() +
			// ",\"suffix\":\"III\",\"password\":\"M1ng1L4r2\",\"firstName\":\"Glenn\",\"userId\":\""+
			// companyUser.getUserId()
			// +"\",\"email\":\"gvpinto@gmail.com\",\"priPhone\":\"9194553262\",\"fax\":\"\",\"lastName\":\"Pinto\",\"entitlements\":[\"NONE\",\"ADD_USER\"],\"active\":true,\"secPhone\":\"9194553263\",\"role\":\"OWNER\",\"title\":\"Mr.\",\"middleInitial\":\"J\",\"approved\":true,\"companyId\":1}";
			// assertEquals(expectedValue,
			// mapper.writeValueAsString(companyUser));
			String actualValue = mapper.writeValueAsString(companyUser);
//			System.out.println(actualValue);
			Assert.assertTrue(actualValue.indexOf(companyUser.getUsername()) >= 0);
			Assert.assertTrue(actualValue.indexOf(companyUser.getPassword()) >= 0);
			Assert.assertTrue(actualValue.indexOf(companyUser.getFirstName()) >= 0);
			Assert.assertTrue(actualValue.indexOf(companyUser.getLastName()) >= 0);
			Assert.assertTrue(actualValue.indexOf(companyUser
					.getMiddleInitial()) >= 0);
			Assert.assertTrue(actualValue.indexOf(String.valueOf(companyUser
					.getUpdateTs().getTime())) >= 0);
			Assert.assertTrue(actualValue.indexOf(companyUser.getPriPhone()) >= 0);
			Assert.assertTrue(actualValue.indexOf(companyUser.getSecPhone()) >= 0);
//			Assert.assertTrue(actualValue.indexOf("\"approved\":true") >= 0);
			Assert.assertTrue(actualValue.indexOf("\"enabled\":true") >= 0);
			Assert.assertTrue(actualValue.indexOf("\"accountNonExpired\":true") >= 0);
			Assert.assertTrue(actualValue.indexOf("\"credentialsNonExpired\":true") >= 0);
			Assert.assertTrue(actualValue.indexOf("\"accountNonLocked\":true") >= 0);
			Assert.assertTrue(actualValue.indexOf("\"authorities\":[{\"authority\":\"ROLE_OWNER\"}]") >= 0);

		} catch (Exception e) {
			Assert.fail("Exception occurred: " + e.getMessage());
		}
	}

	@Test
	public void testDeserializeCompanyUser() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JavaType companyUserType = TypeFactory.defaultInstance()
					.constructType(CompanyUser.class);
			Assert.assertTrue(mapper.canDeserialize(companyUserType));
			String content = "{\"id\":null,\"suffix\":\"III\",\"password\":\"M1ng1L4r2\",\"firstName\":\"Glenn\",\"username\":\"gvpinto571\",\"email\":\"gvpinto@gmail.com\",\"priPhone\":\"9194553262\",\"fax\":\"\",\"lastName\":\"Pinto\",\"middleInitial\":\"J\",\"updateTs\":1325438740341,\"secPhone\":\"9194553263\",\"title\":\"Mr.\",\"deleteTs\":null,\"updateUid\":\"gvpinto571\",\"companyId\":1,\"authorities\":[{\"authority\":\"ROLE_USER\"},{\"authority\":\"ROLE_ADD_LOCATION\"}],\"accountNonExpired\":true,\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"enabled\":true}";
			CompanyUser companyUser = mapper
					.readValue(content, companyUserType);
			assertEquals("gvpinto571", companyUser.getUsername());
			assertEquals("Glenn", companyUser.getFirstName());
			assertEquals("Pinto", companyUser.getLastName());
			assertTrue(companyUser.getAuthorities().contains(Role.USER));
			assertTrue(companyUser.getAuthorities().contains(Role.ADD_LOCATION));
			assertTrue(companyUser.isAccountNonExpired());
			assertTrue(companyUser.isAccountNonLocked());
			assertTrue(companyUser.isCredentialsNonExpired());
			assertTrue(companyUser.isEnabled());
			// LOGGER.debug(companyUser.getEntitlements().toString());
//			assertEquals("[NONE, ADD_USER]", companyUser.getEntitlements()
//					.toString());

		} catch (Exception e) {
			System.out.println(">>>> " + e.getLocalizedMessage());
			Assert.fail("canDeserializeCompanyUser failed: " + e.getMessage());
		}
	}

	@Test
	public void testSerializeLocation() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Location location = AdminDomainData.getLocation();
			Assert.assertTrue(mapper.canSerialize(Location.class));
			// LOGGER.info(mapper.writeValueAsString(location));
			String actualValue = mapper.writeValueAsString(location);
			Assert.assertTrue(actualValue.indexOf(location.getName()) >= 0);
			Assert.assertTrue(actualValue.indexOf(location.getEmail()) >= 0);
			Assert.assertTrue(actualValue.indexOf(location.getDescription()) >= 0);
			Assert.assertTrue(actualValue
					.indexOf("\"parkFacilityType\":\"COVERED\"") >= 0);
			Assert.assertTrue(actualValue.indexOf(location.getStreet()) >= 0);

		} catch (Exception e) {
			Assert.fail("Exception occurred: " + e.getMessage());
		}
	}

	@Test
	public void testDeserializeLocation() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JavaType locationType = TypeFactory.defaultInstance()
					.constructType(Location.class);
			Assert.assertTrue(mapper.canDeserialize(locationType));
			String content = "{\"name\":\"Glenn's parking lot. this-is a meaning, and_w\",\"id\":null,\"timestamp\":1324598045937,\"normName\":\"glenn's parking lot. this-is a meaning, and_w\",\"email\":\"gvpinto@gmail.co.in\",\"priPhone\":\"9194553262\",\"fax\":\"9194470110\",\"secPhone\":\"9194553262\",\"description\":\"This is a beautiful parking lot with ample spaces and a secured place with parking\",\"street\":\"12808 Baybriar Dr, Ste 200\",\"street2\":\"\",\"city\":\"Raleigh\",\"zipCd\":\"27560-5500\",\"stateCd\":\"NC\",\"countryCd\":\"USA\",\"gcLat\":35.910126,\"gcLng\":78.717636,\"parkFacilityType\":\"COVERED\",\"totalCapacity\":100,\"defaultRate\":556,\"manned\":true,\"mannedDesc\":\"This is a Manned place with 24hrs of security\"}";
			Location location = mapper.readValue(content, locationType);
			assertEquals("Glenn's parking lot. this-is a meaning, and_w", location.getName());
		} catch (Exception e) {
			Assert.fail("Exception occurred: " + e.getMessage());
		}

	}
	
	@Test
	public void testGrantedAuthoritySetContainsConditions() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(Role.ADMIN);
		assertFalse(authorities.contains(Role.OWNER));
		authorities.add(Role.OWNER);
		assertTrue(authorities.contains((Role.OWNER)));
	}
	
	@Test
	public void testCompanyAndCompanyUserSplit() {
		String username = "GOODCOMP@gvpinto";
		String[] tokens = username.split("@");
		assertTrue(tokens.length == 2);
		assertEquals("GOODCOMP", tokens[0]);
		assertEquals("gvpinto", tokens[1]);
		
		username = "GOODCOMPgvpinto";
		tokens = username.split("@");
		assertTrue(tokens.length == 1);
		
		username = "";
		tokens = username.split("@");
		assertTrue(tokens.length == 1);
		
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
