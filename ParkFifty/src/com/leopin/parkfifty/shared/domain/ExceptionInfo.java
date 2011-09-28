package com.leopin.parkfifty.shared.domain;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Objects;

@Component
@Scope("prototype")
public class ExceptionInfo implements Serializable{
	
	private String key;
	private String description;
	
	public ExceptionInfo() {
		this("error.default", "Unknown Error has occurred");
	}
	
	public  ExceptionInfo(String key, String description) {
		this.key = key;
		this.description = description;
	}

	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	
	@Override
	public boolean equals(Object object) {
		
		if (object instanceof ExceptionInfo) {
			
			ExceptionInfo that = (ExceptionInfo) object;
			return Objects.equal(this.key, that.key);
			
		} 
		
		return false;
	}
	

	@Override
	public int hashCode() {
		return Objects.hashCode(this.key);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("Error Key", this.key)
				.add("Error Description", this.description)
				.toString();
	}
}
