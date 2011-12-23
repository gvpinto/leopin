package com.leopin.parkfifty.server.services;

import static com.leopin.parkfifty.shared.exception.ErrorKeys.ERROR_APP_ADMIN_COMPANY_EXISTS;
import static com.leopin.parkfifty.shared.exception.ErrorKeys.ERROR_APP_ADMIN_COMPANY_NOT_FOUND;
import static com.leopin.parkfifty.shared.exception.ErrorKeys.ERROR_APP_ADMIN_LOCATION_EXISTS;
import static com.leopin.parkfifty.shared.exception.ErrorKeys.ERROR_APP_ADMIN_USER_EXISTS;
import static com.leopin.parkfifty.shared.exception.ErrorKeys.ERROR_SYS_ADMIN_ADD_COMPANY;
import static com.leopin.parkfifty.shared.exception.ErrorKeys.ERROR_SYS_ADMIN_ADD_LOCATION;
import static com.leopin.parkfifty.shared.exception.ErrorKeys.ERROR_SYS_ADMIN_ADD_USER;
import static com.leopin.parkfifty.shared.exception.ErrorKeys.ERROR_SYS_ADMIN_DELETE_COMPANY;
import static com.leopin.parkfifty.shared.exception.ErrorKeys.ERROR_SYS_ADMIN_GET_COMPANY;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.leopin.parkfifty.shared.domain.Company;
import com.leopin.parkfifty.shared.domain.CompanyUser;
import com.leopin.parkfifty.shared.domain.Entitlement;
import com.leopin.parkfifty.shared.domain.Location;
import com.leopin.parkfifty.shared.domain.Role;
import com.leopin.parkfifty.shared.domain.jsonwrapper.NewCompanyWrapper;
import com.leopin.parkfifty.shared.exception.AppException;
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
				throw new AppException(ERROR_APP_ADMIN_COMPANY_NOT_FOUND, new Object[] {name});
			return company;
		} catch (AppException ae) {
			throw ae;
		} catch (Exception e) {
			throw new SysException(e, ERROR_SYS_ADMIN_GET_COMPANY, new Object[] {name});
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
				throw new AppException(ERROR_APP_ADMIN_COMPANY_NOT_FOUND, new Object[] {String.valueOf(id)});
			return company;
		} catch (AppException ae) {
			throw ae;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new SysException(e, ERROR_SYS_ADMIN_GET_COMPANY, new Object[] {String.valueOf(id)});
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
			
			// Look up if a company exists in the datastore with the Normalized Name which is all lower characters.
			// Normalized name is required because datastore does not have query capabilities for lower casing existing data
			Company c = ofyGet.query(Company.class).filter("normName", company.getNormName()).get();
			if (c != null) {
				throw new AppException(ERROR_APP_ADMIN_COMPANY_EXISTS, new Object[] {company.getName()});
			}
			ofyAdd.put(company);
			ofyAdd.getTxn().commit();
			LOGGER.debug("After adding company {}", company);
			return company;
		} catch (AppException ae) {
			throw ae;
		} catch (Exception ex) {
			throw new SysException(ex, ERROR_SYS_ADMIN_ADD_COMPANY, new Object[] {company.getName()});
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
				throw new AppException(ERROR_APP_ADMIN_COMPANY_NOT_FOUND, new Object[] {id});
			}
			ofy.delete(c);
			ofy.getTxn().commit();
		} catch (AppException ae) {
			throw ae;
		} catch (Exception ex) {
			throw new SysException(ex, ERROR_SYS_ADMIN_DELETE_COMPANY, new Object[] {id});
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
				throw new AppException(ERROR_APP_ADMIN_COMPANY_NOT_FOUND, new Object[] {name});
			}
			ofy.delete(c);
			ofy.getTxn().commit();
		} catch (AppException ae) {
			throw ae;
		} catch (Exception ex) {
			throw new SysException(ex, ERROR_SYS_ADMIN_DELETE_COMPANY, new Object[] {name});
		} finally {
			if (ofy.getTxn().isActive())
				ofy.getTxn().rollback();
			
		}
		
	}


	/**
	 * Add a parking location for a company
	 */
	@Override
	public Location addLocation(Location location) {
		Objectify ofyAdd = objectifyFactory.beginTransaction();
		Objectify ofyGet = objectifyFactory.begin();
		
		try {
			LOGGER.debug(location.toString());
			// Look up if a company exists in the datastore with the same name
			Location c = ofyGet.query(Location.class).filter("normName", location.getNormName()).get();
			if (c != null) {
				throw new AppException(ERROR_APP_ADMIN_LOCATION_EXISTS, new Object[] {location.getName()});
			}
			ofyAdd.put(location);
			ofyAdd.getTxn().commit();
			return location;
		} catch (AppException ae) {
			throw ae;
		} catch (Exception ex) {
			throw new SysException(ex, ERROR_SYS_ADMIN_ADD_LOCATION, new Object[] {location.getName()});
		} finally {
			if (ofyAdd.getTxn().isActive())
				ofyAdd.getTxn().rollback();
			
		}
	}

	/**
	 * Add a User to a Company
	 * @param companyUser Company User object with all necessary data required for persisting
	 * @return CompanyUser returns the updated CompanyUser object which includes a newly created key
	 */
	@Override
	public CompanyUser addCompanyUser(CompanyUser companyUser) {
		Objectify ofyAdd = objectifyFactory.beginTransaction();
		Objectify ofyGet = objectifyFactory.begin();
		
		try {
			
			
			// Check for duplicate userId within a given company.
//			CompanyUser companyUser = ofyGet.get(companyUser.getCompanyKey()), CompanyUser.class, companyUser.getCompanyId());
			CompanyUser cu= ofyGet.query(CompanyUser.class)
					.ancestor(companyUser.getCompanyIdKey())
					.filter("userId", companyUser.getUserId())
					.get();
			
			if (cu != null) {
				throw new AppException(ERROR_APP_ADMIN_USER_EXISTS, new Object[] {companyUser.getUserId()});
			}
			ofyAdd.put(companyUser);
			ofyAdd.getTxn().commit();
			LOGGER.debug("Company User After Insert {}", companyUser);
			return companyUser;
		} catch (AppException ae) {
			throw ae;
		} catch (Exception ex) {
			throw new SysException(ex, ERROR_SYS_ADMIN_ADD_USER, new Object[] {companyUser.getUserId()});
		} finally {
			if (ofyAdd.getTxn().isActive())
				ofyAdd.getTxn().rollback();
			
		}
	}

	@Override
	public NewCompanyWrapper addNewCompany(NewCompanyWrapper newCompanyWrapper) {
		
		Objectify ofyAdd = objectifyFactory.beginTransaction();
		Objectify ofyGet = objectifyFactory.begin();
		
		try {
			
			// Adding the new company
			Company c = ofyGet.query(Company.class).filter("normName", newCompanyWrapper.getCompany().getNormName()).get();
			if (c != null) {
				throw new AppException(ERROR_APP_ADMIN_COMPANY_EXISTS, new Object[] {newCompanyWrapper.getCompany().getName()});
			}
			ofyAdd.put(newCompanyWrapper.getCompany());

			LOGGER.debug("Added Company {} Sucessfully before commit", newCompanyWrapper.getCompany());

			// Adding the owner of the company
//			CompanyUser cu= ofyGet.query(CompanyUser.class)
//					.ancestor(companyUser.getCompanyIdKey())
//					.filter("userId", companyUser.getUserId())
//					.get();
			
//			if (cu != null) {
//				throw new AppException(ERROR_APP_ADMIN_USER_EXISTS, new Object[] {companyUser.getUserId()});
//			}
			
			newCompanyWrapper.getCompanyUser().setRole(Role.OWNER);
			newCompanyWrapper.getCompanyUser().addEntitlement(Entitlement.NOT_APPLICABLE);
			newCompanyWrapper.getCompanyUser().setCompanyId(newCompanyWrapper.getCompany().getId());
			newCompanyWrapper.getCompanyUser().setActive(true);
			newCompanyWrapper.getCompanyUser().setApproved(true);
			
			ofyAdd.put(newCompanyWrapper.getCompanyUser());

			LOGGER.debug("Added Company Owner {} successfully before commit", newCompanyWrapper.getCompanyUser());
			
			ofyAdd.getTxn().commit();
			
			
			LOGGER.info("Added the new company {} successfully with id {}",  newCompanyWrapper.getCompany().getName(),  newCompanyWrapper.getCompany().getId());
			return newCompanyWrapper;
			
		} catch (AppException ae) {
			throw ae;
		} catch (Exception ex) {
			throw new SysException(ex, ERROR_SYS_ADMIN_ADD_COMPANY, new Object[] {newCompanyWrapper.getCompany().getName()});
		} finally {
			if (ofyAdd.getTxn().isActive())
				ofyAdd.getTxn().rollback();
			
		}
	}	

}
