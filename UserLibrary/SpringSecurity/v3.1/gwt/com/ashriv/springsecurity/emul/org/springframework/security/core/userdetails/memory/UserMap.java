/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
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

package org.springframework.security.core.userdetails.memory;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;


/**
 * Used by {@link InMemoryDaoImpl} to store a list of users and their corresponding granted authorities.
 * <p>
 * Usernames are used as the lookup key and are stored in lower case, to allow case-insensitive lookups. So this class
 * should not be used if usernames need to be case-sensitive.
 *
 * @author Ben Alex
 * @deprecated Use a plain map instead
 */
@Deprecated
public class UserMap {
    //~ Static fields/initializers =====================================================================================

    private static final Log logger = LogFactory.getLog(UserMap.class);

    //~ Instance fields ================================================================================================

    private final Map<String, UserDetails> userMap = new HashMap<String, UserDetails>();

    //~ Methods ========================================================================================================

    /**
     * Adds a user to the in-memory map.
     *
     * @param user the user to be stored
     *
     * @throws IllegalArgumentException if a null User was passed
     */
    public void addUser(UserDetails user) throws IllegalArgumentException {
        Assert.notNull(user, "Must be a valid User");

        logger.info("Adding user [" + user + "]");
        this.userMap.put(user.getUsername().toLowerCase(), user);
    }

    /**
     * Locates the specified user by performing a case insensitive search by username.
     *
     * @param username to find
     *
     * @return the located user
     *
     * @throws UsernameNotFoundException if the user could not be found
     */
    public UserDetails getUser(String username) throws UsernameNotFoundException {
        UserDetails result = this.userMap.get(username.toLowerCase());

        if (result == null) {
            throw new UsernameNotFoundException("Could not find user: " + username, username);
        }

        return result;
    }

    /**
     * Indicates the size of the user map.
     *
     * @return the number of users in the map
     */
    public int getUserCount() {
        return this.userMap.size();
    }

    /**
     * Set the users in this {@link UserMap}. Overrides previously added users.
     *
     * @param users {@link Map} &lt;{@link String}, {@link UserDetails}> with pairs (username, userdetails)
     * @since 1.1
     */
    public void setUsers(Map<String, UserDetails> users) {
        userMap.clear();
        for (Map.Entry<String, UserDetails> entry : users.entrySet()) {
            userMap.put(entry.getKey().toLowerCase(), entry.getValue());
        }
    }
}
