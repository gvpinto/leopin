package com.leopin.parkfifty.server.controllers;

import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.google.common.base.CharMatcher;
import com.leopin.parkfifty.server.services.AdminService;
import com.leopin.parkfifty.shared.domain.Company;
import com.leopin.parkfifty.shared.domain.CompanyAndUser;
import com.leopin.parkfifty.shared.domain.CompanyUser;
import com.leopin.parkfifty.shared.domain.ExceptionInfo;
import com.leopin.parkfifty.shared.domain.Location;
import com.leopin.parkfifty.shared.exception.AppErrorKey;
import com.leopin.parkfifty.shared.exception.AppException;
import com.leopin.parkfifty.shared.exception.SysErrorKey;
import com.leopin.parkfifty.shared.exception.SysException;

@Controller
@RequestMapping("/admin")
public class AdminController {

	// TODO Replace LOGGER with Aspects
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	private AdminService adminService;	
	private MessageSource messages;
	private Locale locale;

	
	public AdminController() {
		super();
	}
	

	@Autowired
	Validator validator;
	
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
	 * GET - Retrieve Company by ID or Name 
	 * @param id Identifier that can be an Record ID or Name
	 * @return
	 */
	@RequestMapping(value="/company/{id}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Company getCompany(@PathVariable String id) {
		LOGGER.debug("Retrieving [" + id + "]");
		if (CharMatcher.DIGIT.matchesAllOf(id)) {
			LOGGER.debug("IF");
			return adminService.getCompany(Long.parseLong(id));
		}
		else {
			LOGGER.debug("ELSE");
			return adminService.getCompany(id);
		}
//		(CharMatcher.is('+').replaceFrom(id, ' '))
		
	}
	
	/**
	 * POST - Insert a new Company and 
	 * @param company
	 */
	@RequestMapping(value="/company", 
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody CompanyAndUser addCompany(@RequestBody CompanyAndUser companyAndUser) {
		
		LOGGER.debug("Adding Company [" + companyAndUser.toString() + "]");
		Set<ConstraintViolation<CompanyAndUser>> constraints = validator.validate(companyAndUser);
		if (!constraints.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			boolean comma = false;
			for (ConstraintViolation<CompanyAndUser> constraintViolation : constraints) {
				sb.append((comma? ", " : "") + constraintViolation.getMessage());
				comma = true;
			}
			LOGGER.debug(sb.toString());
			throw new AppException(AppErrorKey.ADMIN_COMPANY_BINDING_ERRORS.getErrorKey(), new Object[]{sb.toString()});
		}

		companyAndUser = adminService.addNewCompany(companyAndUser);
		
		return companyAndUser;

	}

	/**
	 * POST - Insert a new Location
	 * @param company
	 */
	@RequestMapping(value="/company/location", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Location addLocation(@RequestBody Location location) {
		LOGGER.debug("Adding Location [" + location.toString() + "]");
		Set<ConstraintViolation<Location>> constraints = validator.validate(location);
		if (!constraints.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			boolean comma = false;
			for (ConstraintViolation<Location> constraintViolation : constraints) {
				sb.append((comma? ", " : "") + constraintViolation.getMessage());
				comma = true;
			}
			LOGGER.debug(sb.toString());
			throw new AppException(AppErrorKey.ADMIN_COMPANY_BINDING_ERRORS.getErrorKey(), new Object[]{sb.toString()});
		}
		
		return adminService.addLocation(location);
	}
	

	/**
	 * POST - Insert a new Location
	 * @param company
	 */
	@RequestMapping(value="/company/companyUser", 
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
//	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody CompanyUser addCompanyUser(@RequestBody CompanyUser companyUser) {
		LOGGER.debug("Adding CompanyUser [" + companyUser.toString() + "]");
		Set<ConstraintViolation<CompanyUser>> constraints = validator.validate(companyUser);
		if (!constraints.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			boolean comma = false;
			for (ConstraintViolation<CompanyUser> constraintViolation : constraints) {
				sb.append((comma? ", " : "") + constraintViolation.getMessage());
				comma = true;
			}
			LOGGER.debug(sb.toString());
			throw new AppException(AppErrorKey.ADMIN_COMPANY_BINDING_ERRORS.getErrorKey(), new Object[]{sb.toString()});
		}
		
		return adminService.addCompanyUser(companyUser);

	}
	
	/**
	 * POST - Insert a new Location
	 * @param company
	 */
	@RequestMapping(value="/company/companyUser/{userId}", 
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
//	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody CompanyUser getCompanyUser(@PathVariable String userId) {
		LOGGER.debug("Get CompanyUser by [" + userId + "]");
		//TODO: Validation for the User Id
		return adminService.getCompanyUser(userId);

	}

	@RequestMapping(value="/company/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteCompany(@PathVariable String id) {
		LOGGER.debug("Deleting [" + id + "]");
		if (CharMatcher.DIGIT.matchesAllOf(id)) 
			adminService.deleteCompany(Long.parseLong(id));
		else 
			adminService.deleteCompany(id);
	}


	/**
	 * Exception handling for any application notification that was anticipated by the application
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(AppException.class)
	@ResponseStatus(HttpStatus.METHOD_FAILURE)
	public @ResponseBody ExceptionInfo handleThrowable(AppException ex) {
		String message = getMessage(ex.getErrorKey(), ex.getPlaceholderValues());
		LOGGER.error(ex.getErrorKey() + ": " + message);
		return new ExceptionInfo(ex.getErrorKey(), message);
	}
	
	/**
	 * Exception handling for any application exception that was NOT anticipated by the application
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(SysException.class)
	@ResponseStatus(HttpStatus.METHOD_FAILURE)
	public @ResponseBody ExceptionInfo handleThrowable(SysException ex) {
		LOGGER.error(ex.getErrorKey(), ex);
		return new ExceptionInfo(ex.getErrorKey(), getMessage(ex.getErrorKey(), ex.getPlaceholderValues()));
	}
	
	/**
	 * Exception handling for any System runtime exception not anticipated by the program
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public @ResponseBody ExceptionInfo handleThrowable(Exception ex) {
		LOGGER.error("Exception", ex);
		return new ExceptionInfo(SysErrorKey.UNEXPECTED.getErrorKey(), getMessage(SysErrorKey.UNEXPECTED.getErrorKey(), new Object[]{}));
	}
	
	
	private String getMessage(String key, Object[] placeholderValues) {
		return this.messages.getMessage(key, placeholderValues, locale);
	}
	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
}
