package com.leopin.parkfifty.shared.domain;

import java.io.Serializable;

import com.google.common.base.Objects;


public class Company implements Serializable {
	
	private long id;
	private String name;
	private ContactInfo contactInfo;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	@Override
	public boolean equals(Object object) {
		
		if (object instanceof Company) {
			Company that = (Company) object;
			return Objects.equal(this.name, that.name)
					&& Objects.equal(this.contactInfo, that.contactInfo);
		}
		
		return false;
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.name, this.contactInfo);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("Company name", this.name)
				.add("Contact Info", this.contactInfo)
				.toString();
	}
}
