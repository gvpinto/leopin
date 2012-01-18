package com.leopin.parkfifty.config;

import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerExceptionResolver;

import com.leopin.parkfifty.server.Shared;
import com.leopin.parkfifty.server.controllers.Controllers;

@Profile("dev")
@Configuration
@EnableWebMvc
@Import(value={ServiceConfig.class})
@ComponentScan(basePackageClasses={Controllers.class, Shared.class})
@PropertySource(value={})
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/parkfifty/**").addResourceLocations("/parkfifty/");
	}
	
	@Bean
	public ResourceBundleMessageSource messages() {
		ResourceBundleMessageSource rbms = 
				new ResourceBundleMessageSource();
		String[] messageFileNames = {"messages"};
		rbms.setBasenames(messageFileNames);
		return rbms;	
	}
		
	@Bean
	public AcceptHeaderLocaleResolver localeResolver() {
		return new AcceptHeaderLocaleResolver();
	}

	@Bean
	public AnnotationMethodHandlerExceptionResolver exceptionMessageAdapter() {
		AnnotationMethodHandlerExceptionResolver amher = new AnnotationMethodHandlerExceptionResolver();
		HttpMessageConverter<?>[] hmc = new HttpMessageConverter[] {
			new MappingJacksonHttpMessageConverter()
		};
		
		amher.setMessageConverters(hmc);
		return amher;
	}
	
	@Bean
	public Validator validator() {
		return  new LocalValidatorFactoryBean();
	}
}

