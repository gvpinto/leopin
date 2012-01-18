package com.leopin.parkfifty.shared.domain;

import java.util.Date;

public interface CompanyProxy{
	public Long getId();
	public void setId(Long id);
	
	public String getName();
	public void setName(String name);
	
	public String getUrl();
	public void setUrl(String url);

	public String getEmail();
	public void setEmail(String email);

	public String getPriPhone();
	public void setPriPhone(String priPhone);
	
	public String getSecPhone();
	public void setSecPhone(String secPhone);

	public String getFax();
	public void setFax(String fax);

	public Date getUpdateTs();
	public void setUpdateTs(Date updateTs);

	public String getUpdateUid();
	public void setUpdateUid(String updateUid);

}
