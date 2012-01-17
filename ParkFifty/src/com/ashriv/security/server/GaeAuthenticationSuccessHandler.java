package com.ashriv.security.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class GaeAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {

	private static final Logger LOG = LoggerFactory.getLogger(GaeAuthenticationSuccessHandler.class);
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		LOG.info("Authentication Successful for User " + authentication.getPrincipal());
		response.setStatus(HttpServletResponse.SC_OK);

	}

}
