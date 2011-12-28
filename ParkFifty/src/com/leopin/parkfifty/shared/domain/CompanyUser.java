package com.leopin.parkfifty.shared.domain;

import static com.leopin.parkfifty.shared.AppRegExp.EMAIL;
import static com.leopin.parkfifty.shared.AppRegExp.EMPTY_STRING;
import static com.leopin.parkfifty.shared.AppRegExp.MIDDLEINITIAL;
import static com.leopin.parkfifty.shared.AppRegExp.NAME;
import static com.leopin.parkfifty.shared.AppRegExp.PASSWORD;
import static com.leopin.parkfifty.shared.AppRegExp.PHONE_NUM;
import static com.leopin.parkfifty.shared.AppRegExp.SUFFIX;
import static com.leopin.parkfifty.shared.AppRegExp.TITLE;
import static com.leopin.parkfifty.shared.AppRegExp.USER_ID;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.google.common.base.Objects;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Serialized;
import com.googlecode.objectify.annotation.Unindexed;
import com.leopin.parkfifty.shared.utils.Utils;

/**
 * This domain object defines the Company User information
 * @author Glenn Pinto
 */
@Entity
@Unindexed
public class CompanyUser {
	
	@Id
	private Long id;
	
	/**
	 * Reference to the Company Entity
	 */
	@JsonIgnore
	@Indexed
	@Parent
	private Key<Company> companyKey;
	
	@Indexed
	@NotNull(message="{com.leopin.contraints.userid.invalid}")
	@Pattern(regexp=USER_ID, message="{com.leopin.contraints.userid.invalid}")
	private String userId;

	@NotNull(message="{com.leopin.contraints.password.invalid}")
	@Pattern(regexp=PASSWORD, message="{com.leopin.contraints.password.invalid}")
	private String password;
	
	/**
	 * OWNER(1) - Owner, SUPER_ADMIN(2) - Super Admin, ADMIN(3)- Admin, USER(4) - User
	 */
//	@NotNull(message="{com.leopin.contraints.role.invalid}")
//	@Pattern(regexp=ROLE, message="{com.leopin.contraints.role.invalid}")
	@Indexed
	private Role role;
	
	/**
	 * Applicable only for Admin and User's
	 */
	
	@Serialized private List<Entitlement> entitlements;

	@NotNull(message="{com.leopin.contraints.title.invalid}")
	@Pattern(regexp=TITLE, message="{com.leopin.contraints.title.invalid}")	
	private String title;
	
	@NotNull(message="{com.leopin.contraints.firstname.invalid}")
	@Pattern(regexp=NAME, message="{com.leopin.contraints.firstname.invalid}")		
	private String firstName;

	@NotNull(message="{com.leopin.contraints.lastname.invalid}")
	@Pattern(regexp=NAME, message="{com.leopin.contraints.lastname.invalid}")		
	private String lastName;
	
	@NotNull(message="{com.leopin.contraints.middleinitial.invalid}")
	@Pattern(regexp=MIDDLEINITIAL, message="{com.leopin.contraints.middleinitial.invalid}")			
	private String middleInitial;

	@NotNull(message="{com.leopin.contraints.suffix.invalid}")
	@Pattern(regexp=SUFFIX + "|" + EMPTY_STRING, message="{com.leopin.contraints.suffix.invalid}")			
	private String suffix;

	@NotNull(message="{com.leopin.contraints.primary.phone.invalid}")
	@Pattern(regexp=PHONE_NUM, message="{com.leopin.contraints.primary.phone.invalid}")
	private String priPhone;
	
	@NotNull(message="{com.leopin.contraints.secondary.phone.invalid}")
	@Pattern(regexp=PHONE_NUM + "|" + EMPTY_STRING, message="{com.leopin.contraints.secondary.phone.invalid}")
	private String secPhone;
	
	@NotNull(message="{com.leopin.contraints.fax.invalid}")
	@Pattern(regexp=PHONE_NUM + "|" + EMPTY_STRING, message="{com.leopin.contraints.fax.invalid}")
	private String fax;

	/**
	 * Is the user still active or disabled
	 */
	private boolean active;
	
	/**
	 * Is the user an approved user by the owner
	 * Default is always false for user created by any other user except for the owner
	 */
	private boolean approved;
	
	@NotNull(message="{com.leopin.contraints.email.invalid}")
	@Pattern(regexp=EMAIL, message="{com.leopin.contraints.email.invalid}")
	private String email;
	
	@NotNull(message="{com.leopin.contraints.timestamp.invalid}")
	private Date timestamp;

	public CompanyUser() {
		this.timestamp = new Date();
		this.entitlements = new ArrayList<Entitlement>();
		this.entitlements.add(Entitlement.NONE);
		this.role = Role.USER;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@JsonDeserialize(contentAs=Entitlement.class)
	public void setEntitlements(List<Entitlement> entitlements) {
//		if (entitlements != null && entitlements.size() > 0) {
//			this.entitlements.clear();
//			Collections.copy(this.entitlements, entitlements);
//		}
		this.entitlements = entitlements;
	}
	
	public void addEntitlement(Entitlement entitlement) {
		if (entitlement != null) {
			this.entitlements.add(entitlement);
		}
	}
	
	public List<Entitlement> getEntitlements() {
		return entitlements;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	@JsonIgnore
	public Long getCompanyId() {
		return companyKey.getId();
	}
	
	@JsonIgnore
	public Key<Company> getCompanyKey() {
		return companyKey;
	}

	@JsonIgnore
	public Long getCompanyIdFromKey() {
		return companyKey.getId();
	}
	
	@JsonIgnore
	public void setCompanyKey(Key<Company> key) {
		this.companyKey = key;
	}
	
	public void setCompanyKey(Long id) {
		if (id > 0L) {
			this.companyKey = new Key<Company>(Company.class, id);
		}
	}

	
	@Override
	public boolean equals(Object object) {
		
		if (object instanceof CompanyUser) {
			
			CompanyUser that = (CompanyUser) object;
			return Objects.equal((this.companyKey != null ? this.companyKey.getId() : 0L), (that.companyKey != null ? that.companyKey.getId() : 1L))
					&& Objects.equal(this.userId, that.userId)
					&& Objects.equal(this.password, that.password)
					&& Objects.equal(this.role, that.role)
					&& Objects.equal(this.entitlements, that.entitlements)
					&& Objects.equal(this.title, that.title)
					&& Objects.equal(this.firstName, that.firstName)
					&& Objects.equal(this.lastName, that.lastName)
					&& Objects.equal(this.middleInitial, that.middleInitial)
					&& Objects.equal(this.suffix, that.suffix)
					&& Objects.equal(this.priPhone, that.priPhone)
					&& Objects.equal(this.secPhone, that.secPhone)
					&& Objects.equal(this.fax, that.fax)
					&& Objects.equal(this.active, that.active)
					&& Objects.equal(this.approved, that.approved)
					&& Objects.equal(this.email, that.email)
					&& Objects.equal(this.timestamp, that.timestamp);
			
		}
		
		return false;
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.companyKey, this.userId);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("Id", this.id)
				.add("Company Id",(this.companyKey != null ? this.companyKey.getId() : 0L))				
				.add("User Id", this.userId)
				.add("Role", this.role)
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
	
	
}
