package com.leopin.parkfifty.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.leopin.parkfifty.shared.domain.Company;
import com.leopin.parkfifty.shared.domain.ContactInfo;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	
	@Autowired private ObjectifyFactory objectifyFactory;
	
	public AdminServiceImpl() {
		super();
	}
	
	@Override
	public List<Company> getCompanies() {
		List<Company> companies = new ArrayList<Company>();
		for (int i = 0; i < 9; i++) {
			companies.add(getCompany(i));
		}
		return companies;
	}

	@Override
	public Company getCompany(String name) {
		return getCompany(9);
	}

	@Override
	public Company getCompany(Long id) {
		return getCompany(id.intValue());
	}
	
	
	private Company getCompany(int i) {
		Company company = new Company();
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setStreet1("1280" + i + " Baybriar");
		contactInfo.setCity("Raleigh");
		contactInfo.setStateCd("NC");
		contactInfo.setCountryCd("USA");
		contactInfo.setZipcode("2761" + i);
		company.setContactInfo(contactInfo);
		return company;
	}

	@Override
	public void addCompany(Company company) {
		Objectify ofy = objectifyFactory.begin();
		
		
	}

}
