package com.leopin.parkfifty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.spring.ObjectifyFactoryBean;
import com.leopin.parkfifty.server.services.AdminService;
import com.leopin.parkfifty.server.services.AdminServiceImpl;


@Profile("dev")
@Configuration
//@ComponentScan(basePackages="com.leopin.parkfifty.server.service")
@PropertySource("classpath:/com/leopin/parkfifty/server/properties/parkfifty.properties")
public class ServiceConfig {
	
	// Usage: env.getProperty("bean.name") or
	// @Value("${bean.name}") String beanName;
	@Autowired Environment env;
	
	@Bean
	public ObjectifyFactory objectifyFactory() throws Exception {
		ObjectifyFactoryBean objectifyFactoryBean = new ObjectifyFactoryBean();
		objectifyFactoryBean.setBasePackage("com.leopin.parkfifty.shared.domain");
		return objectifyFactoryBean.getObject();
	}
	
	@Bean
	public AdminService adminService() throws Exception {
		AdminServiceImpl adminService =  new AdminServiceImpl();
//		ObjectifyFactory objectifyFactory = objectifyFactory();
//		Assert.notNull(objectifyFactory);
		return adminService;
	}
//	@Bean AdminService adminService() {
//		AdminService adminService = mock(AdminService.class);
//		NewCompanyWrapper newCompanyWrapper = AdminDomain.getNewCompanyWrapper();
//		newCompanyWrapper.getCompany().setId(25L);
//		newCompanyWrapper.getCompanyUser().setId(52L);
//		newCompanyWrapper.getCompanyUser().setCompanyId(25L);
//		when(adminService.addNewCompany((NewCompanyWrapper) anyObject())).thenReturn(newCompanyWrapper);
//		when(adminService.getCompany(1L)).thenReturn(AdminDomain.getCompany());
//		doThrow(new AppException(ERROR_APP_ADMIN_COMPANY_NOT_FOUND, new Object[] {String.valueOf(2L)})).when(adminService.getCompany(2L));
//		return adminService;
//		
//	}
	
	
	
//	@Bean
//	public ObjectifyFactory objectifyFactory() {
//		ObjectifyFactory objectifyFactory = mock(ObjectifyFactory.class);
//		Objectify ofy = mock(Objectify.class);
//		
//		when(objectifyFactory.begin()).thenReturn(ofy);
//		
//		Query<Company> query = mock(Query.class);
//		when(ofy.query(Company.class)).thenReturn(query);
//		when(query.filter(anyString(), anyLong())).thenReturn(query);
////		when(query.filter(anyString(), anyLong()).get()).thenReturn(company);
//		
//		return objectifyFactory;
//	}
	
//	@Bean
//	public ObjectifyFactory objectifyFactory() throws Exception {
//		ObjectifyFactoryBean objectifyFactoryBean = new ObjectifyFactoryBean();
//		objectifyFactoryBean.setBasePackage("com.leopin.parkfifty.shared.domain");
//		return objectifyFactoryBean.getObject();
//	}

//	@Bean
//	public ObjectifyFactory objectifyFactory() {
//		ObjectifyFactory objectifyFactory = mock(ObjectifyFactory.class);
//		Objectify ofy = mock(Objectify.class);
//		
//		Company company = new Company();
//		company.setName("The First Parkfifty Company");
//		company.setUrl("http://www.thefirstparkfiftycompany.com");
//		company.setEmail("thefirstparkfiftycompany@gmail.com");
//		company.setPriPhone("9194553262");
//		company.setFax("9194553263");
//		company.setId(10L);
//		company.setTimestamp(new Date());
//		
//		when(objectifyFactory.begin()).thenReturn(ofy);
//		
//		Query<Company> query = mock(Query.class);
//		when(ofy.query(Company.class)).thenReturn(query);
//		when(query.filter(anyString(), anyLong())).thenReturn(query);
//		when(query.filter(anyString(), anyLong()).get()).thenReturn(company);
//		
//		return objectifyFactory;
//	}

	
}
