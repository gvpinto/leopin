package com.leopin.parkfifty.shared.domain;

import java.math.BigDecimal;

import com.google.gwt.thirdparty.guava.common.base.Objects;

public class ContactInfo {
	
	private String street1;
	private String street2;
	private String city;
	private String stateCd;
	private String countryCd;
	private String zipcode;
	private BigDecimal gcLat;
	private BigDecimal gcLng;
	private String email;
	private String url;
	private String phonePri;
	private String phoneSec;
	private String fax;
	
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateCd() {
		return stateCd;
	}
	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}
	public String getCountryCd() {
		return countryCd;
	}
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public BigDecimal getGcLat() {
		return gcLat;
	}
	public void setGcLat(BigDecimal gcLat) {
		this.gcLat = gcLat;
	}
	public BigDecimal getGcLng() {
		return gcLng;
	}
	public void setGcLng(BigDecimal gcLng) {
		this.gcLng = gcLng;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPhonePri() {
		return phonePri;
	}
	public void setPhonePri(String phonePri) {
		this.phonePri = phonePri;
	}
	public String getPhoneSec() {
		return phoneSec;
	}
	public void setPhoneSec(String phoneSec) {
		this.phoneSec = phoneSec;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}

	@Override
	public boolean equals(Object object) {
		
		if (object instanceof ContactInfo) {
			
			ContactInfo that = (ContactInfo) object;
			return Objects.equal(this.street1, that.street1)
					&& Objects.equal(this.city, that.city)
					&& Objects.equal(this.stateCd, that.stateCd)
					&& Objects.equal(this.zipcode, that.zipcode);
			
		} 
		
		return false;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.street1, this.city, this.stateCd, this.zipcode);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("Street1", this.street1)
				.add("City", this.city)
				.add("State Cd", this.stateCd)
				.add("ZipCode", this.zipcode)
				.toString();
	}
	
	
}
