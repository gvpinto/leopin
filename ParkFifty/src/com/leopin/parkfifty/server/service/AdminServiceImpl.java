package com.leopin.parkfifty.server.service;

import static com.leopin.parkfifty.shared.exception.ErrorKeys.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.leopin.parkfifty.shared.domain.Company;
import com.leopin.parkfifty.shared.exception.AppException;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	// TODO Replace Logger with AspectJ code
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
	
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
		
		Objectify ofy = objectifyFactory.begin();
		
		try {
			Company company = ofy.query(Company.class).filter("name", name).get();
			return company;
		} catch (Exception e) {
			throw new AppException(e, ERROR_ADMIN_GET_COMPANYBYNAME, new Object[] {name});
		}
		
	
	}

	@Override
	public Company getCompany(Long id) {
		return getCompany(id.intValue());
	}
	
	
	private Company getCompany(int i) {
		Company company = new Company();
//		ContactInfo contactInfo = new ContactInfo();
//		contactInfo.setStreet1("1280" + i + " Baybriar");
//		contactInfo.setCity("Raleigh");
//		contactInfo.setStateCd("NC");
//		contactInfo.setCountryCd("USA");
//		contactInfo.setZipcode("2761" + i);
		company.setId(i);
		company.setName("Park Fifty");
		return company;
	}

	@Override
	public void addCompany(Company company) {
		
		Objectify ofy = objectifyFactory.beginTransaction();
		
		try {
			ofy.put(company);
			ofy.getTxn().commit();
		} catch (Exception ex) {
			throw new AppException(ex, ERROR_ADMIN_ADD_COMPANY, new Object[] {company.getName()});
		} finally {
			if (ofy.getTxn().isActive())
				ofy.getTxn().rollback();
			
		}
		
	}
	
	
	private Objectify getNonTransObjectify() {
		return objectifyFactory.beginTransaction();
	}
	
	private Objectify getTransObjectify() {
		return objectifyFactory.begin();
	}

}
