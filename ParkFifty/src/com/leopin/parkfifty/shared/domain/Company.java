package com.leopin.parkfifty.shared.domain;

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
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.CharMatcher;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;

// TODO @Indexed and @UnIndexed on domain Classes and Properties 
@Component
@Scope("prototype")
@Entity
@Unindexed
public class Company implements Serializable {
	
	// ^[a-z0-9_-]{3,16}$ - User ID
	// ^[a-z0-9_-]{6,18}$ - Password
	// ^#?([a-f0-9]{6}|[a-f0-9]{3})$ - Matching a Hex Value
	// ^[a-z0-9-]+$ - Matching a Slug
	// ^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$ - Matching and Email
	// ^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$ - URL
	// ^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$ - IP Address
	// ^<([a-z]+)([^<]+)*(?:>(.*)<\/\1>|\s+\/>)$ - HTML tag
	
	@Id Long id;
	
	@Indexed
	@NotNull
	@Pattern(regexp=COMPANY_NAME, message="Invalid Company Name Structure. Can be Alphanumeric with dashes, underscore or spaces and should be between 3 and 100 characters long.")
	String name;
	
	@NotNull
	@Pattern(regexp=URL, message="Invalid URL address.")
	String url;
	
	@NotNull
	@Pattern(regexp=EMAIL, message="Invalid email address.")
	String email;
	
	@NotNull
	@Pattern(regexp=PHONE_NUM, message="Invalid primary phone number. Can be 9 to 15 digits long.")
	String priPhone;
	
	@NotNull
	@Pattern(regexp=PHONE_NUM + "|" + EMPTY_STRING, message="Invalid secondary phone number. Can be 9 to 15 digits long.")
	String secPhone;
	
	@NotNull
	@Pattern(regexp=PHONE_NUM + "|" + EMPTY_STRING, message="Invalid fax number. Can be 9 to 15 digits long.")
	String fax;
	
	@NotNull
	Date timestamp;
	
	public Company() {
		this.timestamp = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
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
		this.priPhone = CharMatcher.JAVA_LETTER_OR_DIGIT.retainFrom(Strings.nullToEmpty(priPhone));
	}

	public String getSecPhone() {
		return secPhone;
	}

	public void setSecPhone(String secPhone) {
		this.secPhone = CharMatcher.JAVA_LETTER_OR_DIGIT.retainFrom(Strings.nullToEmpty(secPhone));
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = CharMatcher.JAVA_LETTER_OR_DIGIT.retainFrom(Strings.nullToEmpty(fax));
	}


	@Override
	public boolean equals(Object object) {
		
		if (object instanceof Company) {
			Company that = (Company) object;
			return Objects.equal(this.name, that.name);
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
				.add("Name", this.name)
				.add("URL", this.url)
				.add("Email", this.email)
				.add("Pri Phone", this.priPhone)
				.add("Sec Phone", this.secPhone)
				.add("Fax", this.fax)
				.toString();
	}
}
