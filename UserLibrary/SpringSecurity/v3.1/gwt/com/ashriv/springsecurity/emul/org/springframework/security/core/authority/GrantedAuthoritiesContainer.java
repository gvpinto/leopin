package org.springframework.security.core.authority;

import java.io.Serializable;
import java.util.*;

import org.springframework.security.core.GrantedAuthority;

/**
 * Indicates that a object stores GrantedAuthority objects.
 * <p>
 * Typically used in a pre-authenticated scenario when an AuthenticationDetails instance may also be
 * used to obtain user authorities.
 *
 * @author Ruud Senden
 * @author Luke Taylor
 * @since 2.0
 */
public interface GrantedAuthoritiesContainer extends Serializable {
    Collection<? extends GrantedAuthority> getGrantedAuthorities();
}
