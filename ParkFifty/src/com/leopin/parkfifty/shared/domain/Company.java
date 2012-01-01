package com.leopin.parkfifty.shared.domain;

import static com.leopin.parkfifty.shared.AppRegExp.COMPANY_CODE;
import static com.leopin.parkfifty.shared.AppRegExp.COMPANY_NAME;
import static com.leopin.parkfifty.shared.AppRegExp.EMAIL;
import static com.leopin.parkfifty.shared.AppRegExp.EMPTY_STRING;
import static com.leopin.parkfifty.shared.AppRegExp.PHONE_NUM;
import static com.leopin.parkfifty.shared.AppRegExp.URL;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.stereotype.Component;

import com.google.common.base.Objects;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;
import com.leopin.parkfifty.shared.ApplicationVersion;
import com.leopin.parkfifty.shared.utils.Utils;

/**
 * Domain object that defines Company information
 * @author Glenn Pinto
 *
 */

// TODO @Indexed and @UnIndexed on domain Classes and Properties 
@Component
@Entity
@Unindexed
public class Company implements Serializable {
	
	private static final long serialVersionUID = ApplicationVersion.SERIAL_VERSION_UID;
	
	// ^[a-z0-9_-]{3,16}$ - User ID
	// ^[a-z0-9_-]{6,18}$ - Password
	// ^#?([a-f0-9]{6}|[a-f0-9]{3})$ - Matching a Hex Value
	// ^[a-z0-9-]+$ - Matching a Slug
	// ^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$ - Matching and Email
	// ^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$ - URL
	// ^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$ - IP Address
	// ^<([a-z]+)([^<]+)*(?:>(.*)<\/\1>|\s+\/>)$ - HTML tag
	
	@Id 
	Long id;
	
//	/** 
//	 * Company Code will be used for Quick Search and authentication
//	 * It should be 4 to 10 characters starting from with a character and can include -
//	 * both digits and characters
//	 */
//	@Indexed
//	@NotNull(message="{com.leopin.contraints.company.code.invalid}")
//	@Pattern(regexp=COMPANY_CODE, message="{com.leopin.contraints.company.code.invalid}")
//	String code;
	
	@NotNull(message="{com.leopin.contraints.company.name.invalid}")
	@Pattern(regexp=COMPANY_NAME, message="{com.leopin.contraints.company.name.invalid}")
	String name;
	
	@Indexed
	@JsonIgnore
	@NotNull(message="{com.leopin.contraints.company.name.invalid}")
	@Pattern(regexp=COMPANY_NAME, message="{com.leopin.contraints.company.name.invalid}")
	String normName;

	@NotNull(message="{com.leopin.contraints.url.invalid}")
	@Pattern(regexp=URL, message="{com.leopin.contraints.url.invalid}")
	String url;
	
	@NotNull(message="{com.leopin.contraints.email.invalid}")
	@Pattern(regexp=EMAIL, message="{com.leopin.contraints.email.invalid}")
	String email;
	
	@NotNull(message="{com.leopin.contraints.primary.phone.invalid}")
	@Pattern(regexp=PHONE_NUM, message="{com.leopin.contraints.primary.phone.invalid}")
	String priPhone;
	
	@NotNull(message="{com.leopin.contraints.secondary.phone.invalid}")
	@Pattern(regexp=PHONE_NUM + "|" + EMPTY_STRING, message="{com.leopin.contraints.secondary.phone.invalid}")
	String secPhone;
	
	@NotNull(message="{com.leopin.contraints.fax.invalid}")
	@Pattern(regexp=PHONE_NUM + "|" + EMPTY_STRING, message="{com.leopin.contraints.fax.invalid}")
	String fax;
	
	@NotNull(message="{com.leopin.contraints.timestamp.invalid}")
	Date updateTs;
	
	String updateUid;

	public Company() {
		this.updateTs = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		// Code can't be null - invalid state
//		assert(code != null);
//		this.code = code.toUpperCase();
//	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setNormName(name.toLowerCase());
	}

	
	public String getNormName() {
		return normName;
	}

	public void setNormName(String normName) {
		this.normName = normName;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	
	public Date getUpdateTs() {
		return updateTs;
	}

	public void setUpdateTs(Date updateTs) {
		this.updateTs = updateTs;
	}

	public String getUpdateUid() {
		return updateUid;
	}

	public void setUpdateUid(String updateUid) {
		this.updateUid = updateUid;
	}

	@Override
	public boolean equals(Object object) {
		
		if (object instanceof Company) {
			Company that = (Company) object;
			return Objects.equal(this.name, that.name);
//					&& Objects.equal(this.code, that.code)
//					&& Objects.equal(this.url, that.url)
//					&& Objects.equal(this.email, that.email)
//					&& Objects.equal(this.priPhone, that.priPhone)
//					&& Objects.equal(this.secPhone, that.secPhone)
//					&& Objects.equal(this.fax, that.fax)
//					&& Objects.equal(this.updateTs, that.updateTs);
		}
		
		return false;
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.name);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("Id", this.id)
//				.add("Code", this.code)
				.add("Name", this.name)
				.add("Normalized Name", this.normName)
				.add("URL", this.url)
				.add("Email", this.email)
				.add("Pri Phone", this.priPhone)
				.add("Sec Phone", this.secPhone)
				.add("Fax", this.fax)
				.add("Update TS", this.updateTs)
				.toString();
	}
}
