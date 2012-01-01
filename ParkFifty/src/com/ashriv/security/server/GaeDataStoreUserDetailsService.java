package com.ashriv.security.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.leopin.parkfifty.shared.domain.Company;
import com.leopin.parkfifty.shared.domain.CompanyUser;
import com.leopin.parkfifty.shared.exception.AppErrorKey;
import com.leopin.parkfifty.shared.exception.AppException;
import com.leopin.parkfifty.shared.exception.SysErrorKey;
import com.leopin.parkfifty.shared.exception.SysException;

public class GaeDataStoreUserDetailsService implements UserDetailsService {

	/**
	 * Retrieve Company User by User ID
	 * 
	 * @param userId
	 *            User ID
	 * @return CompanyUser Company User Details
	 */

	// TODO Replace Logger with AspectJ code
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GaeDataStoreUserDetailsService.class);

	@Autowired
	private ObjectifyFactory objectifyFactory;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		Objectify ofy = objectifyFactory.begin();
		CompanyUser companyUser = null;
		
		if (username == null) {
			throw new UsernameNotFoundException("Invalid Credentials");
		}

		try {
			
			companyUser = ofy.query(CompanyUser.class)
					.filter("username", username)
					.get();
			
			if (companyUser == null)
				throw new UsernameNotFoundException(
						AppErrorKey.ADMIN_COMPANYUSER_USER_NOT_FOUND
								.getErrorKey());
			
			companyUser.setCompanyKey(new Key<Company>(Company.class, companyUser.getId()));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new UsernameNotFoundException(
					SysErrorKey.ADMIN_GET_USER.getErrorKey());
		}
		
		return companyUser;
	}

}
