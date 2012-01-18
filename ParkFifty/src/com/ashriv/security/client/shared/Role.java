package com.ashriv.security.client.shared;

import org.springframework.security.core.GrantedAuthority;

import com.leopin.parkfifty.server.ApplicationVersion;

public enum Role implements GrantedAuthority {
	
	OWNER(0), 			// 1
	SUPER_ADMIN(1),		// 2
	ADMIN(2), 			// 4
	USER(3), 			// 8
	ADD_LOCATION(4), 	// 16
	ADD_RATE(6), 		// 32
	ADD_DISCOUNT(6);	// 64

	private static final long serialVersionUID = ApplicationVersion.SERIAL_VERSION_UID;
	private final int bit;

	Role(int bit) {
		this.bit = bit;
	}

	public int getBit() {
		return this.bit;
	}

	@Override
	public String getAuthority() {
		return toString();
	}
}
