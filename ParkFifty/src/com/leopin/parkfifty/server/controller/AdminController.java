package com.leopin.parkfifty.server.controller;

import static org.apache.commons.lang.StringUtils.*;
import static com.leopin.parkfifty.shared.exception.ErrorKeys.*;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.leopin.parkfifty.server.service.AdminService;
import com.leopin.parkfifty.shared.domain.Company;
import com.leopin.parkfifty.shared.domain.ExceptionInfo;
import com.leopin.parkfifty.shared.exception.AppException;

@Controller
@RequestMapping("/admin")
public class AdminController {

	// TODO Replace LOGGER with Aspects
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	private AdminService adminService;
	private MessageSource messages;
	private Locale locale;

	
	@Autowired
	public AdminController(AdminService service, 
			MessageSource messages) {
		this.adminService = service;
		this.messages = messages;
	}
	
	@RequestMapping(value="/locale")
	public void resolveLocale(HttpServletRequest request) {
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request); 
		if (localeResolver != null) {
			this.locale = localeResolver.resolveLocale(request);
		} else {
			this.locale = Locale.US;
		}
	}
	
	@RequestMapping(value="/company", method=RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody String getName() {
		return "Glenn Pinto";
		
	}
	
	/**
	 * Retrieve Company by ID or Name
	 * @param id Identifier that can be an Record ID or Name
	 * @return
	 */
	@RequestMapping(value="/company/{id}", method=RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody Company getCompany(@PathVariable String id) {
		LOGGER.info("ID[" + id + "]");
		if (isNumeric(id)) 
			return adminService.getCompany(Long.parseLong(id));
		else 
			return adminService.getCompany(id);
			
		
	}
	
	@RequestMapping(value="/company", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addCompany(@RequestBody Company company) {
		adminService.addCompany(company);
	}

	
	@ExceptionHandler(AppException.class)
	@ResponseStatus(HttpStatus.METHOD_FAILURE)
	public @ResponseBody ExceptionInfo handleThrowable(AppException ex) {
		LOGGER.error(ex.getErrorKey(), ex);
		return new ExceptionInfo(ex.getErrorKey(), this.messages.getMessage(ex.getErrorKey(), ex.getPlaceholderValues(), locale));
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public @ResponseBody ExceptionInfo handleThrowable(Exception ex) {
		LOGGER.error("Exception", ex);
		return new ExceptionInfo(ERROR_UNEXPECTED, this.messages.getMessage(ERROR_UNEXPECTED, new Object[]{}, locale));
	}
	
}
