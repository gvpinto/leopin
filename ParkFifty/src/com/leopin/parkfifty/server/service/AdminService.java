package com.leopin.parkfifty.server.service;

import java.util.List;

import com.leopin.parkfifty.shared.domain.Company;
import com.leopin.parkfifty.shared.domain.Location;

public interface AdminService {
	
	List<Company> getCompanies();
	
	Company getCompany(String name);
	
	Company getCompany(Long id);

	Company addCompany(Company company);

	void deleteCompany(Long id);

	void deleteCompany(String name);

	Location addLocation(Location location);

}
