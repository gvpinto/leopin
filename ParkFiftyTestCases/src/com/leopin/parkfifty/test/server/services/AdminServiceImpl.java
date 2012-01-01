package com.leopin.parkfifty.test.server.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.leopin.parkfifty.server.services.AdminService;
import com.leopin.parkfifty.shared.domain.Company;
import com.leopin.parkfifty.shared.domain.CompanyAndUser;
import com.leopin.parkfifty.shared.domain.CompanyUser;
import com.leopin.parkfifty.shared.domain.Location;
import com.leopin.parkfifty.shared.exception.AppErrorKey;
import com.leopin.parkfifty.shared.exception.AppException;
import com.leopin.parkfifty.shared.exception.SysErrorKey;
import com.leopin.parkfifty.shared.exception.SysException;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	// TODO Replace Logger with AspectJ code
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired 
	private ObjectifyFactory objectifyFactory;
	
//	public void setObjectifyFactory(ObjectifyFactory objectifyFactory) {
//		this.objectifyFactory = objectifyFactory;
//	}
//	
//	public AdminServiceImpl() {
//		super();
//	}
	
	@Override
	public List<Company> getCompanies() {
		List<Company> companies = new ArrayList<Company>();
		for (int i = 0; i < 9; i++) {
			companies.add(getCompany(Long.valueOf(i)));
		}
		return companies;
	}

	/**
	 * Retrieve Company by name
	 * @param name Company name
	 * @return Company
	 */
	public Company getCompany(String name) {
		
		Objectify ofy = objectifyFactory.begin();
		
		try {
			Company company = ofy.query(Company.class).filter("name", name).get();
			if (company == null)
				throw new AppException(AppErrorKey.ADMIN_COMPANY_NOT_FOUND.getErrorKey(), new  Object[] {name});
			return company;
		} catch (AppException ae) {
			throw ae;
		} catch (Exception e) {
			throw new SysException(e, SysErrorKey.ADMIN_GET_COMPANY.getErrorKey(), new  Object[] {name});
		}
		
	
	}
	
	/**
	 * Retrieve Company by Key
	 * @param id Company Id
	 * @return Company
	 */
	@Override
	public Company getCompany(Long id) {
		Objectify ofy = objectifyFactory.begin();
		
		try {
			Company company = ofy.query(Company.class).filter("id", id).get();
			if (company == null)
				throw new AppException(AppErrorKey.ADMIN_COMPANY_NOT_FOUND.getErrorKey(), new  Object[] {String.valueOf(id)});
			return company;
		} catch (AppException ae) {
			throw ae;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new SysException(e, SysErrorKey.ADMIN_GET_COMPANY.getErrorKey(), new  Object[] {String.valueOf(id)});
		}
	}

	/**
	 * Add a new Company
	 * @param company Company object
	 */
	@Override
	public Company addCompany(Company company) {
		
		Objectify ofyAdd = objectifyFactory.beginTransaction();
		Objectify ofyGet = objectifyFactory.begin();
		
		try {
			// Look up if a company exists in the datastore with the same name
			Company c = ofyGet.query(Company.class).filter("name", company.getName()).get();
			if (c != null) {
				throw new AppException(AppErrorKey.ADMIN_COMPANY_EXISTS.getErrorKey(), new  Object[] {company.getName()});
			}
			ofyAdd.put(company);
			ofyAdd.getTxn().commit();
			return company;
		} catch (AppException ae) {
			throw ae;
		} catch (Exception ex) {
			throw new SysException(ex, SysErrorKey.ADMIN_ADD_COMPANY.getErrorKey(), new  Object[] {company.getName()});
		} finally {
			if (ofyAdd.getTxn().isActive())
				ofyAdd.getTxn().rollback();
			
		}
		
	}

	/**
	 * Delete Company from the database by Company id
	 * @param id Company id
	 */
	@Override
	public void deleteCompany(Long id) {
		Objectify ofy = objectifyFactory.beginTransaction();
		
		try {
			// Look up if a company exists in the datastore with the same name
			Company c = getCompany(id);
			if (c == null) {
				throw new AppException(AppErrorKey.ADMIN_COMPANY_NOT_FOUND.getErrorKey(), new  Object[] {id});
			}
			ofy.delete(c);
			ofy.getTxn().commit();
		} catch (AppException ae) {
			throw ae;
		} catch (Exception ex) {
			throw new SysException(ex, SysErrorKey.ADMIN_DELETE_COMPANY.getErrorKey(), new  Object[] {id});
		} finally {
			if (ofy.getTxn().isActive())
				ofy.getTxn().rollback();
			
		}
		
	}
	
	/**
	 * Delete Company from the database by Company id
	 * @param id Company id
	 */
	@Override
	public void deleteCompany(String name) {
		Objectify ofy = objectifyFactory.beginTransaction();
		
		try {
			// Look up if a company exists in the datastore with the same name
			Company c = getCompany(name);
			if (c == null) {
				throw new AppException(AppErrorKey.ADMIN_COMPANY_NOT_FOUND.getErrorKey(), new  Object[] {name});
			}
			ofy.delete(c);
			ofy.getTxn().commit();
		} catch (AppException ae) {
			throw ae;
		} catch (Exception ex) {
			throw new SysException(ex, SysErrorKey.ADMIN_DELETE_COMPANY.getErrorKey(), new  Object[] {name});
		} finally {
			if (ofy.getTxn().isActive())
				ofy.getTxn().rollback();
			
		}
		
	}

	@Override
	public Location addLocation(Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompanyUser addCompanyUser(CompanyUser companyUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompanyAndUser addNewCompany(CompanyAndUser addCompany) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	public CompanyUser getCompanyUser(String userId) {
		return null;
	}

}
