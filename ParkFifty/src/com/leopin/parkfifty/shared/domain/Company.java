package com.leopin.parkfifty.shared.domain;

import java.io.Serializable;

import com.google.appengine.repackaged.com.google.common.base.Objects;

public class Company implements Serializable {
	
	private long id;
	private String name;
	private ContactInfo contactInfo;


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
