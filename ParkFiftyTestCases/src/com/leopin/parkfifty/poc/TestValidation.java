package com.leopin.parkfifty.poc;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

import com.leopin.parkfifty.shared.utils.AppRegExp;

public class TestValidation {

	@Email
	String email;
	
	@NotNull
//	@Pattern(regexp="(^[0-9]{10,15}$)|()")
	@Pattern(regexp=AppRegExp.PHONE_NUM)
	String phone;
	
	@Pattern(regexp="(http|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?")
	String url;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
