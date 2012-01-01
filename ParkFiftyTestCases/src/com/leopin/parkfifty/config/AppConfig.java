package com.leopin.parkfifty.config;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.Query;
import com.leopin.parkfifty.shared.domain.Company;

@Configuration
public class AppConfig {
	
//	public @Bean AdminService adminService() {
//		return new AdminServiceImpl();
//	}
	public @Bean ObjectifyFactory objectifyFactory() {
		ObjectifyFactory objectifyFactory = mock(ObjectifyFactory.class);
		Objectify ofy = mock(Objectify.class);
		
		Company company = new Company();
		company.setName("The First Parkfifty Company");
		company.setUrl("http://www.thefirstparkfiftycompany.com");
		company.setEmail("thefirstparkfiftycompany@gmail.com");
		company.setPriPhone("9194553262");
		company.setFax("9194553263");
		company.setId(10L);
		company.setUpdateTs(new Date());
		
		when(objectifyFactory.begin()).thenReturn(ofy);
		
		Query<Company> query = mock(Query.class);
		when(ofy.query(Company.class)).thenReturn(query);
		when(query.filter(anyString(), anyLong())).thenReturn(query);
		when(query.filter(anyString(), anyLong()).get()).thenReturn(company);
		
		return objectifyFactory;
	}
	
}
