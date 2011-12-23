package com.leopin.parkfifty.server.services;

import java.util.List;

import com.leopin.parkfifty.shared.domain.Company;
import com.leopin.parkfifty.shared.domain.CompanyUser;
import com.leopin.parkfifty.shared.domain.Location;
import com.leopin.parkfifty.shared.domain.jsonwrapper.NewCompanyWrapper;

public interface AdminService {
	
	List<Company> getCompanies();
	
	Company getCompany(String name);
	
	Company getCompany(Long id);

	Company addCompany(Company company);

	void deleteCompany(Long id);

	void deleteCompany(String name);

	Location addLocation(Location location);

	CompanyUser addCompanyUser(CompanyUser companyUser);
	
	NewCompanyWrapper addNewCompany(NewCompanyWrapper addCompany);

}
