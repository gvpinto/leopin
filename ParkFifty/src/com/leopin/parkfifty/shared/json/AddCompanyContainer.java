package com.leopin.parkfifty.shared.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.leopin.parkfifty.shared.domain.Company;

@Component
@Scope("prototype")
public class AddCompanyContainer {
	@Autowired
	Company company;

	public Company getCompany() {
		return company;
	}
}
