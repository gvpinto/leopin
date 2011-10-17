package com.leopin.parkfifty.unittest.admin.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

	private static Logger LOGGER = LoggerFactory.getLogger(AdminJsonUnitTests.class);
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
	public void commandProvidingFormControllerWithCustomEditor() throws Exception {
		
		
		Company company = new Company();
		company.setName("Company without errors");
		company.setEmail("gpinto@bbandt.com");
		company.setUrl("http://www.bbt.com");
		company.setPriPhone("9194553262");
//		when(company.getName()).thenReturn("Company without errors");
//		when(company.getEmail()).thenReturn("gpinto@bbandt.com");
//		when(company.getUrl()).thenReturn("http://www.bbt.com");
//		when(company.getPriPhone()).thenReturn("9194553262");

		when(service.getCompany(anyLong())).thenReturn(company);
		
		@SuppressWarnings("serial") DispatcherServlet servlet = new DispatcherServlet() {
			@Override
			protected WebApplicationContext createWebApplicationContext(WebApplicationContext parent) {
				GenericWebApplicationContext wac = new GenericWebApplicationContext();
				RootBeanDefinition controllerDef = new RootBeanDefinition(AdminController.class);
				controllerDef.setAutowireCandidate(true);
				controllerDef.setAutowireMode(RootBeanDefinition.AUTOWIRE_BY_TYPE);
				controllerDef.getPropertyValues().add("adminService", AdminJsonUnitTests.this.service);
				controllerDef.getPropertyValues().add("messages", AdminJsonUnitTests.this.messages);
				wac.registerBeanDefinition("controller",controllerDef);
//				wac.registerBeanDefinition("viewResolver", new RootBeanDefinition(TestViewResolver.class));		
//				RootBeanDefinition jsonConverterDef = new RootBeanDefinition(MappingJacksonHttpMessageConverter.class);
//				MutablePropertyValues mpv = new MutablePropertyValues();
//				mpv.add("supportedMediaTypes", "application/json");
//				jsonConverterDef.setPropertyValues(mpv);
//				wac.registerBeanDefinition("jsonConverter", jsonConverterDef);
				
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
