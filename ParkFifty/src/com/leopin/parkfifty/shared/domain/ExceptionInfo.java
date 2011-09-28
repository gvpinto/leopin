package com.leopin.parkfifty.shared.domain;

import org.springframework.stereotype.Component;

import com.google.common.base.Objects;
import com.leopin.parkfifty.shared.exception.AppException;

@Component
public class ExceptionInfo {
	
	private String description;
	
	public ExceptionInfo() {
		this("Unknown Error has occurred");
	}
	
	public  ExceptionInfo(String description) {
		this.description = description;
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
			return Objects.equal(this.description, that.description);
			
		} 
		
		return false;
	}
	

	@Override
	public int hashCode() {
		return Objects.hashCode(this.description);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("Error Description", this.description)
				.toString();
	}
}
