/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.security.access.hierarchicalroles;

import java.util.Collection;

import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * This class wraps Spring Security's <tt>UserDetails</tt> in a way that its <tt>getAuthorities()</tt> method is
 * delegated to <tt>RoleHierarchy.getReachableGrantedAuthorities</tt>. All other methods are
 * delegated to the <tt>UserDetails</tt> implementation.
 *
 * @author Michael Mayr
 * @deprecated use a {@link RoleHierarchyVoter} or {@code RoleHierarchyAuthoritiesMapper} instead.
 */
public class UserDetailsWrapper implements UserDetails {

    private static final long serialVersionUID = 1532428778390085311L;

    private UserDetails userDetails = null;

    private RoleHierarchy roleHierarchy = null;

    public UserDetailsWrapper(UserDetails userDetails, RoleHierarchy roleHierarchy) {
        this.userDetails = userDetails;
        this.roleHierarchy = roleHierarchy;
    }

    public boolean isAccountNonExpired() {
        return userDetails.isAccountNonExpired();
    }

    public boolean isAccountNonLocked() {
        return userDetails.isAccountNonLocked();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleHierarchy.getReachableGrantedAuthorities(userDetails.getAuthorities());
    }

    public boolean isCredentialsNonExpired() {
        return userDetails.isCredentialsNonExpired();
    }

    public boolean isEnabled() {
        return userDetails.isEnabled();
    }

    public String getPassword() {
        return userDetails.getPassword();
    }

    public String getUsername() {
        return userDetails.getUsername();
    }

    public UserDetails getUnwrappedUserDetails() {
        return userDetails;
    }

}
