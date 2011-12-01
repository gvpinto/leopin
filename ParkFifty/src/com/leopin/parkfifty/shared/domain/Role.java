package com.leopin.parkfifty.shared.domain;

import java.io.Serializable;

public enum Role implements Serializable {
	OWNER(1), SUPER_ADMIN(2), ADMIN(3), USER(4);

	private final int role;
	Role(int role) {
		this.role = role;
	}
	
	public int getRoleId() {
		return role;
	}
}
