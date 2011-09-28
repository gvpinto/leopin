package com.leopin.parkfifty.shared.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Objects;
import com.googlecode.objectify.annotation.Entity;

// TODO @Indexed and @UnIndexed on domain Classes and Properties 
@Component
@Scope("prototype")
@Entity
public class Company implements Serializable {
	
	@Id Long id;
	String name;
	Date timestamp;
	
	public Company() {
		this.timestamp = new Date();
	}
	
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

	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public boolean equals(Object object) {
		
		if (object instanceof Company) {
			Company that = (Company) object;
			return Objects.equal(this.name, that.name);
		}
		
		return false;
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.name);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("Company name", this.name)
				.toString();
	}
}
