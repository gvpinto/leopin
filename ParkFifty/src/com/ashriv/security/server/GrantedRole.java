package com.ashriv.security.server;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import com.leopin.parkfifty.shared.ApplicationVersion;

public class GrantedRole implements GrantedAuthority {

	private static final long serialVersionUID = ApplicationVersion.SERIAL_VERSION_UID;
    private Role role;

    public GrantedRole() {
    	super();
    }

    public GrantedRole(Role role) {
        Assert.hasText(role.getRole(), "A granted authority textual representation is required");
        this.role = role;
    }
    
    @JsonDeserialize(contentAs=Role.class)
    public void setAuthority(Role role) {
    	this.role = role;
    }

    public String getAuthority() {
        return this.role.getRole();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof GrantedRole) {
            return role.equals(((GrantedRole) obj).role);
        }

        return false;
    }

    public int hashCode() {
        return this.role.hashCode();
    }

    public String toString() {
        return this.role.getRole();
    }
}
