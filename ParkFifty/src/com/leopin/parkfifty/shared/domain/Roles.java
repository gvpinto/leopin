package com.leopin.parkfifty.shared.domain;

public enum Roles {
	OWNER(1), SUPER_ADMIN(2), ADMIN(3), USER(4);

	private final int role;
	Roles(int role) {
		this.role = role;
	}
	
	public int getRoleId() {
		return role;
	}
}
