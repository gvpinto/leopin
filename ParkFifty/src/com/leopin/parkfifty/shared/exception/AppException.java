package com.leopin.parkfifty.shared.exception;

import com.google.common.base.Objects;

public class AppException extends RuntimeException {

	private String errorKey;
	private Object[] placeholderValues;

	
	public AppException() {
		this(null, "error.unknown", null);
	}

	public  AppException(String errorKey) {
		this(null, errorKey, null);
	}

	public  AppException(String errorKey, Object[] placeholderValues) {
		this(null, errorKey, placeholderValues);
	}
	
	public  AppException(Throwable th, String errorKey) {
		this(th, errorKey, null);
	}
	
	public  AppException(Throwable th, String errorKey, Object[] placeholderValues) {
		super(th);
		this.errorKey = errorKey;
		this.placeholderValues = placeholderValues;
	}

	
	public Object[] getPlaceholderValues() {
		if (this.placeholderValues == null) 
			return new Object[]{};
		else
			return placeholderValues;
	}

	public void setPlaceholderValues(Object[] placeholderValues) {
		this.placeholderValues = placeholderValues;
	}
	
	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
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
		return Objects.hashCode(this.errorKey);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("Error Key", this.errorKey)
				.toString();
	}
}
