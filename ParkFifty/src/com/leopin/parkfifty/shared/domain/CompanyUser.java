package com.leopin.parkfifty.shared.domain;

import static com.leopin.parkfifty.shared.AppRegExp.COMPANY_CODE;

import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.google.common.base.Objects;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindexed;

/**
 * This domain object defines the Company User information
 * @author Glenn Pinto
 */
@Entity
@Unindexed
public class CompanyUser {

	public CompanyUser() {
		this.timestamp = new Date();
	}
	
	@Id
	private Long id;
	
	/**
	 * Reference to the Company Entity
	 */
	@JsonIgnore
	@Parent
	private Key<Company> company;
	
	@Indexed
	@NotNull(message="{com.leopin.contraints.userid.invalid}")
//	@Pattern(regexp=USER_ID, message="{com.leopin.contraints.userid.invalid}")
	private String userId;
	
	private String password;
	
	private int role;
	
	private String title;
	
	private String firstName;
	
	private String lastName;
	
	private String middleInitial;
	
	private String suffix;
	
	private String priPhone;
	
	private String secPhone;
	
	private String fax;
	
	private Date timestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		id = id;
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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
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
		this.priPhone = priPhone;
	}

	public String getSecPhone() {
		return secPhone;
	}

	public void setSecPhone(String secPhone) {
		this.secPhone = secPhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public boolean equals(Object object) {
		
		if (object instanceof CompanyUser) {
			CompanyUser that = (CompanyUser) object;
			return Objects.equal(this.userId, that.company);
		}
		
		return false;
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.userId, this.company);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("Id", this.id)
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
//				.add("Email", this.email)
				.toString();
	}	
	
	
}
