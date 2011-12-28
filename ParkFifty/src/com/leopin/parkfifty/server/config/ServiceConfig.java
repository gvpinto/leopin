package com.leopin.parkfifty.server.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.spring.ObjectifyFactoryBean;
import com.leopin.parkfifty.server.services.Services;
import com.leopin.parkfifty.shared.domain.Company;
import com.leopin.parkfifty.shared.domain.CompanyUser;
import com.leopin.parkfifty.shared.domain.Location;


@Profile("production")
@Configuration
@ComponentScan(basePackageClasses={Services.class})
@PropertySource("classpath:/com/leopin/parkfifty/server/properties/parkfifty.properties")
public class ServiceConfig {
	
	// Usage: env.getProperty("bean.name") or
	// @Value("${bean.name}") String beanName;
	@Autowired Environment env;
	
	@Bean
	public ObjectifyFactory objectifyFactory() throws Exception {
		ObjectifyFactoryBean objectifyFactoryBean = new ObjectifyFactoryBean();
//		objectifyFactoryBean.setBasePackage("com.leopin.parkfifty.shared.domain");
		List<Class<?>> classes = new ArrayList<Class<?>>();
		classes.add(Company.class);
		classes.add(CompanyUser.class);
		classes.add(Location.class);
		objectifyFactoryBean.setClasses(classes);
		objectifyFactoryBean.afterPropertiesSet();
		return objectifyFactoryBean.getObject();
	}
	
}
