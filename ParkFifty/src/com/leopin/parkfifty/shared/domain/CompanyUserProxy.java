package com.leopin.parkfifty.shared.domain;

import java.util.Date;

public interface CompanyUserProxy {
	public Long getId();

	public void setId(Long id);


	public String getUsername();

	public void setUsername(String username);

	public String getPassword();

	public void setPassword(String password);

	public String getTitle();

	public void setTitle(String title);

	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public String getMiddleInitial();

	public void setMiddleInitial(String middleInitial);

	public String getSuffix();

	public void setSuffix(String suffix);

	public String getPriPhone();

	public void setPriPhone(String priPhone);
	
	public String getSecPhone();
	
	public void setSecPhone(String secPhone);

	public String getFax();

	public void setFax(String fax);

	public String getEmail();

	public void setEmail(String email);

	public Date getUpdateTs();

	public void setUpdateTs(Date updateTs);

	public Date getDeleteTs();

	public void setDeleteTs(Date deleteTs);

	public String getUpdateUid();

	public void setUpdateUid(String updateUid);
}
