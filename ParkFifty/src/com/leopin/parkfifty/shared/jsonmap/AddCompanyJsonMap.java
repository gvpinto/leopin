package com.leopin.parkfifty.shared.jsonmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.leopin.parkfifty.shared.domain.Company;

@Component
@Scope("prototype")
public class AddCompanyJsonMap {
	@Autowired
	Company company;

	public Company getCompany() {
		return company;
	}
}
