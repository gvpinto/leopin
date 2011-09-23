package com.leopin.parkfifty.server.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.leopin.parkfifty.server.service.AdminService;
import com.leopin.parkfifty.shared.domain.Company;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private AdminService adminService;
	
	
	@Autowired
	public AdminController(AdminService service) {
		this.adminService = service;
	}
	
	@RequestMapping(value="/company", method=RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody String getName() {
		return "Glenn Pinto";
		
	}
	
	@RequestMapping(value="/company/{name}", method=RequestMethod.GET)
	public @ResponseBody Company getCompany(@PathVariable String name) {
		logger.info("Name[" + name + "]");
		return adminService.getCompany(name);
	}
	
	@RequestMapping(value="/company", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addCompany(@RequestBody Company company) {
		adminService.addCompany(company);
	}
	
	@ExceptionHandler(Throwable.class)
	
	public String handleThrowable(Throwable.class) {
		
	}

}
