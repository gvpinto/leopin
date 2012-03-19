package org.springframework.security.authentication;

import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * @author Luke Taylor
 */
public class AccountStatusUserDetailsChecker implements UserDetailsChecker {

    protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    public void check(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            throw new LockedException(messages.getMessage("AccountStatusUserDetailsChecker.locked", "User account is locked"), user);
        }

        if (!user.isEnabled()) {
            throw new DisabledException(messages.getMessage("AccountStatusUserDetailsChecker.disabled", "User is disabled"), user);
        }

        if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException(messages.getMessage("AccountStatusUserDetailsChecker.expired",
                    "User account has expired"), user);
        }

        if (!user.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(messages.getMessage("AccountStatusUserDetailsChecker.credentialsExpired",
                    "User credentials have expired"), user);
        }
    }
}
