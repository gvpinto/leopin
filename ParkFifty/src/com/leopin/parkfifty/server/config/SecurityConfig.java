package com.leopin.parkfifty.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.dao.SystemWideSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ashriv.security.server.GaeAuthenticationEntryPoint;
import com.ashriv.security.server.GaeAuthenticationFailureHandler;
import com.ashriv.security.server.GaeAuthenticationSuccessHandler;
import com.ashriv.security.server.GaeDataStoreUserDetailsService;

@Profile("production")
@Configuration
@ImportResource("classpath:parkfifty-security.xml")
public class SecurityConfig {
	
	@Bean
	public AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setSaltSource(systemWideSaltSource());
		return daoAuthenticationProvider;
	}
	
	@Bean UserDetailsService userDetailsService() {
		return new GaeDataStoreUserDetailsService();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new Md5PasswordEncoder();
	}
	
	@Bean
	public SaltSource systemWideSaltSource() {
		SystemWideSaltSource systemWideSaltSource = new SystemWideSaltSource();
		systemWideSaltSource.setSystemWideSalt(passwordSalt());
		return systemWideSaltSource;
	}
	
	@Bean
	public String passwordSalt() {
		return "AB#@SA123SLJasd%%TKLS";
	}
	
	@Bean
	public AuthenticationEntryPoint authEntryPoint() {
		return new GaeAuthenticationEntryPoint();
	}
	
	@Bean
	public AuthenticationFailureHandler authFailureHandler() {
		return new GaeAuthenticationFailureHandler();
	}
	
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler() {
		return new GaeAuthenticationSuccessHandler();
	}

}
