package com.leopin.parkfifty.shared.domain.exception;

import org.springframework.stereotype.Component;

import com.google.common.base.Objects;

@Component
public class AppException extends RuntimeException {
	
	private boolean error;
	private String errorKey;
	private String description;
	
	public AppException() {
		this.error = true;
		this.errorKey = "error.unknown";
		this.description = "Unknown Error has occurred";
	}
	
	public  AppException(boolean error, String errorKey) {
		this.error = error;
		this.errorKey = errorKey;
	}
	
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getErrorKey() {
		return errorKey;
	}
	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	
	@Override
	public boolean equals(Object object) {
		
		if (object instanceof AppException) {
			
			AppException that = (AppException) object;
			return Objects.equal(this.errorKey, that.errorKey);
			
		} 
		
		return false;
	}
	

	@Override
	public int hashCode() {
		return Objects.hashCode(this.error, this.errorKey);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("Is Error", this.error)
				.add("Error Key", this.errorKey)
				.add("Error Description", this.description)
				.toString();
	}
}
