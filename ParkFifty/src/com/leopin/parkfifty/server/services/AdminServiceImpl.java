package com.leopin.parkfifty.server.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.ashriv.security.server.GrantedRole;
import com.ashriv.security.server.Role;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
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
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	String passwordSalt;
	
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
				throw new AppException(AppErrorKey.ADMIN_COMPANY_NOT_FOUND.getErrorKey(), new Object[] {name});
			return company;
		} catch (AppException ae) {
			throw ae;
		} catch (Exception e) {
			throw new SysException(e, SysErrorKey.ADMIN_GET_COMPANY.getErrorKey(), new Object[] {name});
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
				throw new AppException(AppErrorKey.ADMIN_COMPANY_NOT_FOUND.getErrorKey(), new Object[] {String.valueOf(id)});
			return company;
		} catch (AppException ae) {
			throw ae;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new SysException(e, SysErrorKey.ADMIN_GET_COMPANY.getErrorKey(), new Object[] {String.valueOf(id)});
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
				throw new AppException(AppErrorKey.ADMIN_COMPANY_EXISTS.getErrorKey(), new Object[] {company.getName()});
			}
			ofyAdd.put(company);
			ofyAdd.getTxn().commit();
			LOGGER.debug("After adding company {}", company);
			return company;
		} catch (AppException ae) {
			throw ae;
		} catch (Exception ex) {
			throw new SysException(ex, SysErrorKey.ADMIN_ADD_COMPANY.getErrorKey(), new Object[] {company.getName()});
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
				throw new AppException(AppErrorKey.ADMIN_COMPANY_NOT_FOUND.getErrorKey(), new Object[] {id});
			}
			ofy.delete(c);
			ofy.getTxn().commit();
		} catch (AppException ae) {
			throw ae;
		} catch (Exception ex) {
			throw new SysException(ex, SysErrorKey.ADMIN_DELETE_COMPANY.getErrorKey(), new Object[] {id});
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
				throw new AppException(AppErrorKey.ADMIN_COMPANY_NOT_FOUND.getErrorKey(), new Object[] {name});
			}
			ofy.delete(c);
			ofy.getTxn().commit();
		} catch (AppException ae) {
			throw ae;
		} catch (Exception ex) {
			throw new SysException(ex, SysErrorKey.ADMIN_DELETE_COMPANY.getErrorKey(), new Object[] {name});
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
				throw new AppException(AppErrorKey.ADMIN_LOCATION_EXISTS.getErrorKey(), new Object[] {location.getName()});
			}
			ofyAdd.put(location);
			ofyAdd.getTxn().commit();
			return location;
		} catch (AppException ae) {
			throw ae;
		} catch (Exception ex) {
			throw new SysException(ex, SysErrorKey.ADMIN_ADD_LOCATION.getErrorKey(), new Object[] {location.getName()});
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
			if (companyUser.getAuthorities().contains(new GrantedRole(Role.ROLE_OWNER))) {
				throw new AppException(AppErrorKey.ADMIN_COMPANYUSER_OWNER_EXISTS.getErrorKey(), new Object[] {companyUser.getUsername()});
			} else {
				CompanyUser cu= ofyGet.query(CompanyUser.class)
						.ancestor(companyUser.getCompanyKey())
						.filter("userId", companyUser.getUsername())
						.get();
				
				if (cu != null) {
					throw new AppException(AppErrorKey.ADMIN_USER_EXISTS.getErrorKey(), new Object[] {companyUser.getUsername()});
				}
			}

			ofyAdd.put(companyUser);
			ofyAdd.getTxn().commit();
			LOGGER.debug("Company User After Insert {}", companyUser);
			return companyUser;
		} catch (AppException ae) {
			throw ae;
		} catch (Exception ex) {
			throw new SysException(ex, SysErrorKey.ADMIN_ADD_USER.getErrorKey(), new Object[] {companyUser.getUsername()});
		} finally {
			if (ofyAdd.getTxn().isActive())
				ofyAdd.getTxn().rollback();
			
		}
	}

	@Override
	public CompanyAndUser addNewCompany(CompanyAndUser companyAndUser) {
		
		Objectify ofyAdd = objectifyFactory.beginTransaction();
		Objectify ofyGet = objectifyFactory.begin();
		
		try {
			
			// Check if the User Id already exists. Should be unique across companies
			// TODO: Need to check for delete timestamp when looking up Company user. Should not consider those as part of the Uniqueness
			CompanyUser cu= ofyGet.query(CompanyUser.class)
					.ancestor(new Key<Company>(Company.class, companyAndUser.getCompany().getId()))
					.filter("username", companyAndUser.getCompanyUser().getUsername())
					.get();
			
			if (cu != null) {
				throw new AppException(AppErrorKey.ADMIN_USER_EXISTS.getErrorKey(), new Object[] {companyAndUser.getCompanyUser().getUsername()});
			}
			
			// Check if the company already exists. If not Persist it.
			companyAndUser.getCompany().setUpdateUid(companyAndUser.getCompanyUser().getUsername());
			Company c = ofyGet.query(Company.class).filter("normName", companyAndUser.getCompany().getNormName()).get();
			if (c != null) {
				throw new AppException(AppErrorKey.ADMIN_COMPANY_EXISTS.getErrorKey(), new Object[] {companyAndUser.getCompany().getName()});
			}
			ofyAdd.put(companyAndUser.getCompany());

			LOGGER.debug("Added Company {} Sucessfully before commit", companyAndUser.getCompany());
			
			// Set defaults for the User Id treated as an Owner when creating the company
			Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			authorities.add(new GrantedRole(Role.ROLE_OWNER));
			companyAndUser.getCompanyUser().setAuthorities(authorities);
//			companyAndUser.getCompanyUser().addEntitlement(Entitlement.NOT_APPLICABLE);
			companyAndUser.getCompanyUser().setCompanyKey(new Key<Company>(Company.class, companyAndUser.getCompany().getId()));
			companyAndUser.getCompanyUser().setAccountNonExpired(true);
			companyAndUser.getCompanyUser().setAccountNonLocked(true);
			companyAndUser.getCompanyUser().setEnabled(true);
			companyAndUser.getCompanyUser().setCredentialsNonExpired(true);
			
			// Hash the password before storing the user
			companyAndUser.getCompanyUser().setPassword(passwordEncoder.encodePassword(companyAndUser.getCompanyUser().getPassword(), passwordSalt));
			LOGGER.debug("Password: " + companyAndUser.getCompanyUser().getPassword());
			
			// Persist the User
			ofyAdd.put(companyAndUser.getCompanyUser());

			LOGGER.debug("Added Company Owner {} successfully before commit", companyAndUser.getCompanyUser());
			
			ofyAdd.getTxn().commit();
			
			
			LOGGER.info("Added the new company {} successfully with id {}",  companyAndUser.getCompany().getName(),  companyAndUser.getCompany().getId());
			return companyAndUser;
			
		} catch (AppException ae) {
			throw ae;
		} catch (Exception ex) {
			throw new SysException(ex, SysErrorKey.ADMIN_ADD_COMPANY.getErrorKey(), new Object[] {companyAndUser.getCompany().getName()});
		} finally {
			if (ofyAdd.getTxn().isActive())
				ofyAdd.getTxn().rollback();
			
		}
	}

	/**
	 * Retrieve Company User by User ID
	 * @param userId User ID
	 * @return CompanyUser Company User Details
	 */
	public CompanyUser getCompanyUser(String userId) {
		Objectify ofy = objectifyFactory.begin();
		
		try {
			CompanyUser companyUser = ofy.query(CompanyUser.class).filter("userId", userId).get();
			if (companyUser == null)
				throw new AppException(AppErrorKey.ADMIN_COMPANYUSER_USER_NOT_FOUND.getErrorKey(), new Object[] {String.valueOf(userId)});
			return companyUser;
		} catch (AppException ae) {
			throw ae;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new SysException(e, SysErrorKey.ADMIN_GET_USER.getErrorKey(), new Object[] {String.valueOf(userId)});
		}
	}	

}
