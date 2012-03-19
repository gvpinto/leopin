package com.leopin.parkfifty.shared.domain;

import javax.validation.Valid;

import com.leopin.parkfifty.shared.utils.ApplicationVersion;

public class CompanyAndUser {
	
	private static final long serialVersionUID = ApplicationVersion.SERIAL_VERSION_UID;
	
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

	public String toString() {
		return company.toString() + "," + companyUser.toString();
	}
	
}
