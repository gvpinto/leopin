package com.leopin.parkfifty.server.service;

import java.util.List;

import com.leopin.parkfifty.shared.domain.Company;

public interface AdminService {
	
	List<Company> getCompanies();
	
	Company getCompany(String name);
	
	Company getCompany(Long id);

}
