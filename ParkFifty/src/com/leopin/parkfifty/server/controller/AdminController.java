package com.leopin.parkfifty.server.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="/company/${name}", method=RequestMethod.GET)
//	headers = "Content-Type=application/json")
//	@ResponseStatus(HttpStatus.OK)
	@ResponseBody public Company getCompany(@PathVariable String name) {
		logger.info("Name[" + name + "]");
		return adminService.getCompany(name);
	}
	
	

}
