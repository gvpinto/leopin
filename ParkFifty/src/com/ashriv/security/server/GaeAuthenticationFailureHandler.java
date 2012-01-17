package com.ashriv.security.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class GaeAuthenticationFailureHandler implements
		AuthenticationFailureHandler {

	private static final Logger LOG = LoggerFactory.getLogger(GaeAuthenticationFailureHandler.class);
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		LOG.info("Failed to Authenticate " + exception.getAuthentication().getPrincipal());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Failed to Authenticate " + exception.getAuthentication().getPrincipal());

	}

}
