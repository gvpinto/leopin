package com.ashriv.security.server;

import java.io.Serializable;

public enum Role implements Serializable {
	ROLE_OWNER("ROLE_OWNER"), // Can do everything
	ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN"), // Can do everything the OWNER can do except adding SUPER_ADMIN's
	ROLE_ADMIN("ROLE_ADMIN"), // Can do everything the SUPER_ADMIN can do except adding ADMIN's
	ROLE_USER("ROLE_USER"), // Can do only the access given below
	ROLE_ADD_USER("ROLE_ADD_USER"),  
	ROLE_ADD_LOCATION("ROLE_ADD_LOCATION"), 
	ROLE_ADD_DISCOUNT("ROLE_ADD_LOCATION"), 
	ROLE_ADD_RATE("ROLE_ADD_RATE");

	private final String role;
	
	Role(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
}
