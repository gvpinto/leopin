package org.springframework.security.core.authority;

import org.springframework.security.core.GrantedAuthority;

import java.util.*;

/**
 * Indicates that a object can be used to store and retrieve GrantedAuthority objects.
 * <p>
 * Typically used in a pre-authenticated scenario when an AuthenticationDetails instance may also be
 * used to obtain user authorities.
 *
 * @author Ruud Senden
 * @author Luke Taylor
 * @since 2.0
 */
@Deprecated
public interface MutableGrantedAuthoritiesContainer extends GrantedAuthoritiesContainer {
    /**
     * Used to store authorities in the containing object.
     */
    void setGrantedAuthorities(Collection<? extends GrantedAuthority> authorities);
}
