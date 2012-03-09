package com.leopin.parkfifty.client.domain;

import java.util.Date;

import com.leopin.parkfifty.shared.domain.CompanyUserProxy;

public class CompanyUserProxyImpl implements CompanyUserProxy {

	Long id;
	String username;
	String password;
	String title;
	String firstName;
	String lastName;
	String middleInitial;
	String suffix;
	String priPhone;
	String secPhone;
	String fax;
	String email;
	Date updateTs;
	Date deleteTs;
	String updateUid;
	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getMiddleInitial() {
		return this.middleInitial;
	}

	@Override
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	@Override
	public String getSuffix() {
		return this.suffix;
	}

	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public String getPriPhone() {
		return this.priPhone;
	}

	@Override
	public void setPriPhone(String priPhone) {
		this.priPhone = priPhone;
	}

	@Override
	public String getSecPhone() {
		return this.secPhone;
	}

	@Override
	public void setSecPhone(String secPhone) {
		this.secPhone = secPhone;
	}

	@Override
	public String getFax() {
		return this.fax;
	}

	@Override
	public void setFax(String fax) {
		this.fax = fax;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Date getUpdateTs() {
		return this.updateTs;
	}

	@Override
	public void setUpdateTs(Date updateTs) {
		this.updateTs = updateTs;
	}

	@Override
	public Date getDeleteTs() {
		return this.deleteTs;
	}

	@Override
	public void setDeleteTs(Date deleteTs) {
		this.deleteTs = deleteTs;
	}

	@Override
	public String getUpdateUid() {
		return this.updateUid;
	}

	@Override
	public void setUpdateUid(String updateUid) {
		this.updateUid = updateUid;
	}

}
