package com.leopin.parkfifty.shared.domain.jsonwrapper;

import javax.validation.Valid;

import com.leopin.parkfifty.shared.domain.Company;
import com.leopin.parkfifty.shared.domain.CompanyUser;

public class AddCompanyJsonWrapper {
	
	@Valid
	private Company company;
	
	@Valid
	private CompanyUser companyUser;
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public CompanyUser getCompanyUser() {
		return companyUser;
	}

	public void setCompanyUser(CompanyUser companyUser) {
		this.companyUser = companyUser;
	}


	
}
