package com.leopin.parkfifty.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.spring.ObjectifyFactoryBean;


@Profile("production")
@Configuration
@ComponentScan(basePackages="com.leopin.parkfifty.server.service")
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
	
}
