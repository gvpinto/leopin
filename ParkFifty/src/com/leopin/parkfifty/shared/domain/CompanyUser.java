package com.leopin.parkfifty.shared.domain;

import static com.leopin.parkfifty.shared.utils.AppRegExp.EMAIL;
import static com.leopin.parkfifty.shared.utils.AppRegExp.EMPTY_STRING;
import static com.leopin.parkfifty.shared.utils.AppRegExp.MIDDLEINITIAL;
import static com.leopin.parkfifty.shared.utils.AppRegExp.NAME;
import static com.leopin.parkfifty.shared.utils.AppRegExp.PASSWORD;
import static com.leopin.parkfifty.shared.utils.AppRegExp.PHONE_NUM;
import static com.leopin.parkfifty.shared.utils.AppRegExp.SUFFIX;
import static com.leopin.parkfifty.shared.utils.AppRegExp.TITLE;
import static com.leopin.parkfifty.shared.utils.AppRegExp.USER_ID;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.ashriv.security.client.shared.Role;
import com.google.common.base.Objects;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Serialized;
import com.googlecode.objectify.annotation.Unindexed;
import com.leopin.parkfifty.shared.utils.ApplicationVersion;
import com.leopin.parkfifty.shared.utils.Utils;

/**
 * This domain object defines the Company User information
 * @author Glenn Pinto
 */
@Entity
@Unindexed
public class CompanyUser implements UserDetails, CredentialsContainer, CompanyUserProxy, Serializable {
	
	private static final long serialVersionUID = ApplicationVersion.SERIAL_VERSION_UID;
	
    /**
     * Calls the more complex constructor with all boolean arguments set to {@code true}.
     */
	public CompanyUser() {
		setUpdateTs(new Date());
	}
//    public CompanyUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        this(username, password, true, true, true, true, authorities);
//    }
//    
//	public CompanyUser(String username, String password, boolean enabled, boolean accountNonExpired,
//            boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
//		
//		this.updateTs = new Date();
////		this.entitlements = new ArrayList<Entitlement>();
////		this.entitlements.add(Entitlement.NONE);
//
//        if (((username == null) || "".equals(username)) || (password == null)) {
//            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
//        }
//
//        this.username = username;
//        this.password = password;
//        this.enabled = enabled;
//        this.accountNonExpired = accountNonExpired;
//        this.credentialsNonExpired = credentialsNonExpired;
//        this.accountNonLocked = accountNonLocked;
//        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
//		
//	}
	
	@Id
	private Long id;
	
//	/**
//	 * Reference to the Company Entity
//	 */
	@JsonIgnore
	@Indexed
	@Parent
	Key<Company> companyKey;
	Long companyId;
	
	@Indexed
	@NotNull(message="{com.leopin.contraints.userid.invalid}")
	@Pattern(regexp=USER_ID, message="{com.leopin.contraints.userid.invalid}")
	String username;

	@NotNull(message="{com.leopin.contraints.password.invalid}")
	@Pattern(regexp=PASSWORD, message="{com.leopin.contraints.password.invalid}")
	String password;
	
	/**
	 * OWNER(0), 			// 1
	 * SUPER_ADMIN(1)		// 2
	 * ADMIN(2), 			// 4
	 * USER(3), 			// 8
	 * ADD_LOCATION(4), 	// 16
	 * ADD_RATE(5), 		// 32
	 * ADD_DISCOUNT(6);		// 64
	 */
	
//	@NotNull(message="{com.leopin.contraints.role.invalid}")
//	@Pattern(regexp=ROLE, message="{com.leopin.contraints.role.invalid}")
	@Indexed
	@Serialized Set<GrantedAuthority> authorities;
	
	/**
	 * Applicable only for Admin and User's
	 */
	
//	@Serialized List<Entitlement> entitlements;

	@NotNull(message="{com.leopin.contraints.title.invalid}")
	@Pattern(regexp=TITLE, message="{com.leopin.contraints.title.invalid}")	
	String title;
	
	@NotNull(message="{com.leopin.contraints.firstname.invalid}")
	@Pattern(regexp=NAME, message="{com.leopin.contraints.firstname.invalid}")		
	String firstName;

	@NotNull(message="{com.leopin.contraints.lastname.invalid}")
	@Pattern(regexp=NAME, message="{com.leopin.contraints.lastname.invalid}")		
	String lastName;
	
	@NotNull(message="{com.leopin.contraints.middleinitial.invalid}")
	@Pattern(regexp=MIDDLEINITIAL+ "|" + EMPTY_STRING, message="{com.leopin.contraints.middleinitial.invalid}")			
	String middleInitial;

	@NotNull(message="{com.leopin.contraints.suffix.invalid}")
	@Pattern(regexp=SUFFIX + "|" + EMPTY_STRING, message="{com.leopin.contraints.suffix.invalid}")			
	String suffix;

	@NotNull(message="{com.leopin.contraints.primary.phone.invalid}")
	@Pattern(regexp=PHONE_NUM, message="{com.leopin.contraints.primary.phone.invalid}")
	String priPhone;
	
	@NotNull(message="{com.leopin.contraints.secondary.phone.invalid}")
	@Pattern(regexp=PHONE_NUM + "|" + EMPTY_STRING, message="{com.leopin.contraints.secondary.phone.invalid}")
	String secPhone;
	
	@NotNull(message="{com.leopin.contraints.fax.invalid}")
	@Pattern(regexp=PHONE_NUM + "|" + EMPTY_STRING, message="{com.leopin.contraints.fax.invalid}")
	String fax;

	/**
	 * User Account active or expired flag
	 */
	boolean accountNonExpired;
	
	
	/**
	 * User account is locked flag
	 */
	boolean accountNonLocked;
	/**
	 * Is the user still active or disabled
	 */
	
	/**
	 * Have the credentials expired
	 */
	boolean credentialsNonExpired;
	
	/**
	 * Is the user account enabled by the owner
	 * its false by if the user was create someone other than the owner
	 */
	boolean enabled;

	
	@NotNull(message="{com.leopin.contraints.email.invalid}")
	@Pattern(regexp=EMAIL, message="{com.leopin.contraints.email.invalid}")
	String email;
	
	@NotNull(message="{com.leopin.contraints.timestamp.invalid}")
	Date updateTs;
	
	Date deleteTs;
	
	String updateUid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getPriPhone() {
		return priPhone;
	}

	public void setPriPhone(String priPhone) {
		this.priPhone = Utils.scrubPhoneNum(priPhone);
	}

	public String getSecPhone() {
		return secPhone;
	}

	public void setSecPhone(String secPhone) {
		this.secPhone = Utils.scrubPhoneNum(secPhone);
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = Utils.scrubPhoneNum(fax);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getUpdateTs() {
		return updateTs;
	}

	public void setUpdateTs(Date updateTs) {
		this.updateTs = updateTs;
	}

	public Date getDeleteTs() {
		return deleteTs;
	}

	public void setDeleteTs(Date deleteTs) {
		this.deleteTs = deleteTs;
	}

	public String getUpdateUid() {
		return updateUid;
	}

	public void setUpdateUid(String updateUid) {
		this.updateUid = updateUid;
	}

//	@JsonDeserialize(contentAs=Entitlement.class)
//	public void setEntitlements(List<Entitlement> entitlements) {
////		if (entitlements != null && entitlements.size() > 0) {
////			this.entitlements.clear();
////			Collections.copy(this.entitlements, entitlements);
////		}
//		this.entitlements = entitlements;
//	}
//	
//	public void addEntitlement(Entitlement entitlement) {
//		if (entitlement != null) {
//			this.entitlements.add(entitlement);
//		}
//	}
//	
//	public List<Entitlement> getEntitlements() {
//		return entitlements;
//	}
	
	@JsonIgnore
	public Key<Company> getCompanyKey() {
		if (this.companyKey == null) {
			this.companyKey = new Key<Company>(Company.class, getCompanyId());
		}
		return this.companyKey;
	}

	
	@JsonIgnore
	public void setCompanyKey(Key<Company> key) {
		System.out.println("------------------- TEST -----------------------");
		this.companyKey = key;
		this.companyId = key.getId();
	}
		
//	public void generateCompanyKey() {
//		if (this.companyId > 0L) {
//			this.companyKey = new Key<Company>(Company.class, id);
//		}  else {
//			throw new UnableToGenerateKey("Unable to Generate Key for companyId: " + this.companyId);
//		}
//	}

	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@JsonIgnore
	public Long getCompanyIdFromKey() {
		return companyKey.getId();
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
    @JsonDeserialize(contentAs=Role.class)
	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));;
	}


	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}


	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}


	@Override
	public boolean isEnabled() {
		return this.enabled;
	}


	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public void eraseCredentials() {
		password = null;
	}	
	
	@Override
	public boolean equals(Object object) {
		
		if (object instanceof CompanyUser) {
			
			CompanyUser that = (CompanyUser) object;
			return Objects.equal(this.companyId, that.companyId)
					&& Objects.equal(this.username, that.username);
//					&& Objects.equal(this.password, that.password)
//					&& Objects.equal(this.role, that.role)
//					&& Objects.equal(this.entitlements, that.entitlements)
//					&& Objects.equal(this.title, that.title)
//					&& Objects.equal(this.firstName, that.firstName)
//					&& Objects.equal(this.lastName, that.lastName)
//					&& Objects.equal(this.middleInitial, that.middleInitial)
//					&& Objects.equal(this.suffix, that.suffix)
//					&& Objects.equal(this.priPhone, that.priPhone)
//					&& Objects.equal(this.secPhone, that.secPhone)
//					&& Objects.equal(this.fax, that.fax)
//					&& Objects.equal(this.active, that.active)
//					&& Objects.equal(this.approved, that.approved)
//					&& Objects.equal(this.email, that.email)
//					&& Objects.equal(this.updateTs, that.updateTs);
			
		}
		
		return false;
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.companyId, this.username);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("Id", this.id)
				.add("Company Id", this.companyId)				
				.add("User Id", this.username)
				.add("Role", this.authorities)
				.add("Title", this.title)
				.add("First Name", this.firstName)
				.add("Middle Initial", this.middleInitial)
				.add("Last Name", this.lastName)
				.add("Suffix", this.suffix)
				.add("Pri Phone", this.priPhone)
				.add("Sec Phone", this.secPhone)
				.add("Fax", this.fax)
				.add("Email", this.email)
				.toString();
	}



	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        
        // Ensure array iteration order is predictable (as per UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities =
            new TreeSet<GrantedAuthority>(new AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }
    
    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = ApplicationVersion.SERIAL_VERSION_UID;

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to the set.
            // If the authority is null, it is a custom authority and should precede others.
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }
	
	
}
