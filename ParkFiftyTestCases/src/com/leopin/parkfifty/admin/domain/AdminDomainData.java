package com.leopin.parkfifty.admin.domain;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.ashriv.security.client.shared.Role;
import com.leopin.parkfifty.client.domain.CompanyProxyImpl;
import com.leopin.parkfifty.client.domain.CompanyUserProxyImpl;
import com.leopin.parkfifty.shared.domain.Company;
import com.leopin.parkfifty.shared.domain.CompanyAndUser;
import com.leopin.parkfifty.shared.domain.CompanyProxy;
import com.leopin.parkfifty.shared.domain.CompanyUser;
import com.leopin.parkfifty.shared.domain.CompanyUserProxy;
import com.leopin.parkfifty.shared.domain.Location;
import com.leopin.parkfifty.shared.domain.ParkFacilityType;

public class AdminDomainData {

	/**
	 * Get Company template
	 * @return Company
	 */
	
	public static Company getCompany() {
		Random rand = new Random(System.currentTimeMillis());
		
		Company company = new Company();
		int randval = rand.nextInt(9999);
		String val = new DecimalFormat("0000").format(randval);
		
		company.setName("This is a Good Company " + val);
		
//		company.setCode("AGOODC" + val);
		company.setEmail("gpinto@bbandt.com");
		company.setUrl("http://www.ashriv.com");
		company.setPriPhone("(919) 455-3262");
		company.setSecPhone("");
		company.setFax("(919) 447-0110");
		company.setUpdateUid("gvpinto");
		return company;
	}
	/**
	 * Get Company User template data
	 * @return CompanyUser
	 */
	public static CompanyUser getCompanyUser(Long companyId) {
			Random rand = new Random(System.currentTimeMillis());
			int userIdSuffix = rand.nextInt(999);
			CompanyUser companyUser = new CompanyUser();
			companyUser.setUsername("gvpinto" + userIdSuffix);
			companyUser.setPassword("M1ng1L4r2");
//			companyUser.addEntitlement(Entitlement.ADD_USER);
//			companyUser.setRole(Role.OWNER);
			Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			authorities.add(Role.OWNER);
//			authorities.add(new GrantedRole(Role.ADD_LOCATION));
			companyUser.setAuthorities(authorities);
			companyUser.setTitle("Mr.");
			companyUser.setFirstName("Glenn");
			companyUser.setMiddleInitial("J");
			companyUser.setLastName("Pinto");
			companyUser.setSuffix("III");
			companyUser.setPriPhone("(919)455-3262");
			companyUser.setSecPhone("919 455-3263");
			companyUser.setFax("");
			companyUser.setEmail("gvpinto@gmail.com");
			companyUser.setUpdateUid(companyUser.getUsername());
			companyUser.setAccountNonExpired(true);
			companyUser.setAccountNonLocked(true);
			companyUser.setCredentialsNonExpired(true);
			companyUser.setEnabled(true);
			companyUser.setCompanyId(companyId);
			return companyUser;
	}
	
	
	public static CompanyAndUser getCompanyAndUser() {
		CompanyAndUser newCompanyWrapper = new CompanyAndUser();
		newCompanyWrapper.setCompany(getCompany());
		newCompanyWrapper.setCompanyUser(getCompanyUser(0L));
		return newCompanyWrapper;
	}

	/**
	 * get Location template data
	 * @return Location
	 */
	public static Location getLocation() {
		Location location = new Location();
		location.setName("Glenn's parking lot. this-is a meaning, and_w");
		location.setDescription("This is a beautiful parking lot with ample spaces and a secured place with parking");
		location.setStreet("12808 Baybriar Dr, Ste 200");
		location.setStreet2("");
		location.setCity("Raleigh");
		location.setStateCd("NC");
		location.setZipCd("27560-5500");
		location.setCountryCd("USA");
		location.setGcLat(35.910126f);
		location.setGcLng(78.717635f);
		location.setParkFacilityType(ParkFacilityType.COVERED);
		location.setPriPhone("919-455-3262");
		location.setSecPhone("(919) 455-3262");
		location.setFax("919 447 0110");
		location.setEmail("gvpinto@gmail.co.in");
		location.setTotalCapacity(100);
		location.setDefaultRate(556);
		location.setManned(true);
		location.setMannedDesc("This is a Manned place with 24hrs of security");
		return location;
	}
	
	/**
	 * Return Company Object for the GWT client
	 * @return CompanyProxy Company object
	 */
	public static CompanyProxy getCompanyProxy() {
		CompanyProxy company = new CompanyProxyImpl();
		company.setName("The First Park Company");
		company.setUrl("www.thefirstparkcompany.com");
		company.setEmail("thefirstparkcompany@gmail.com");
		company.setPriPhone("(919) 447-0110");
		company.setSecPhone("");
		company.setFax("(919) 404-9291");
		return company;
	}
	
	/**
	 * Return CompanyUser object to the GWT client
	 * @return CompanyUser CompanyUser object
	 */
	public static CompanyUserProxy getCompanyUserProxy() {
		CompanyUserProxy companyUser = new CompanyUserProxyImpl();
		companyUser.setUsername("gvpinto");
		companyUser.setPassword("The#Pass1234");
		companyUser.setTitle("Mr.");
		companyUser.setFirstName("Johnny");
		companyUser.setMiddleInitial("Z");
		companyUser.setLastName("Smith");
		companyUser.setSuffix("");
		companyUser.setEmail("varevadal@gmail.com");
		companyUser.setPriPhone("(919) 455-3263");
		companyUser.setSecPhone("");
		companyUser.setFax("(919) 447-0110");
		return companyUser;
		
	}
	
	

}
