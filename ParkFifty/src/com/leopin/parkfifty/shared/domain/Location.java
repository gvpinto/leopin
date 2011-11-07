package com.leopin.parkfifty.shared.domain;

import static com.leopin.parkfifty.shared.AppRegExp.*;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;

@Entity
@Unindexed
public class Location {
	@Id Long id;
	
	@Indexed
	private String normName;
	
	@NotNull(message="{location.name.invalid}")
	@Pattern(regexp=LOCATION_NAME, message="{location.name.invalid}")		
	private String name;

	@NotNull(message="{location.desc.invalid}")
	@Pattern(regexp=LOCATION_DESC, message="{location.desc.invalid}")	
	private String description;
		
	@NotNull(message="{street.invalid}")
	@Pattern(regexp=STREET, message="{street.invalid}")			
	private String street;

	@NotNull(message="{street.invalid}")
	@Pattern(regexp=STREET + "|" + EMPTY_STRING, message="{street.invalid}")			
	private String street2;

	@NotNull(message="{city.invalid}")
	@Pattern(regexp=CITY, message="{city.invalid}")			
	private String city;
	
	@NotNull(message="{zipCd.invalid}")
	@Pattern(regexp=ZIP_CD, message="{zipCd.invalid}")				
	private String zipCd;
	
	@NotNull(message="{stateCd.invalid}")
	@Pattern(regexp=STATE_CD, message="{stateCd.invalid}")					
	private String stateCd;
	
	@NotNull(message="{countryCd.invalid}")
	@Pattern(regexp=COUNTRY_CD, message="{countryCd.invalid}")						
	private String countryCd;
	
	@NotNull(message="{gc.latitude.invalid}")
	// Customer Validator
	private BigDecimal gcLat;
	
	@NotNull(message="{gc.longitude.invalid}")
	// Customer Validator
	private BigDecimal gcLng;
	
	//C -> Covered, O -> Open, S -> Street
	@NotNull(message="{parking.type.code.invalid}")
	private String parkingTypeCd;
	
	@NotNull(message="{primary.phone.invalid}")
	@Pattern(regexp=PHONE_NUM, message="{primary.phone.invalid}")
	private String priPhone;
	
	@NotNull(message="{secondary.phone.invalid}")
	@Pattern(regexp=PHONE_NUM + "|" + EMPTY_STRING, message="{secondary.phone.invalid}")
	private String secPhone;
	
	@NotNull(message="{fax.invalid}")
	@Pattern(regexp=PHONE_NUM + "|" + EMPTY_STRING, message="{fax.phone.invalid}")
	private String fax;
	
	@NotNull(message="{email.invalid}")
	@Pattern(regexp=EMAIL, message="{email.invalid}")
	private String email;
	
	@Min(value=1)
	private int totalCapacity;
	
	// Default Rate per/hr
	@Min(value=100)
	private long defaultRate;
	
	private boolean manned;
	private String mannedDesc;
	
	@NotNull(message="{timestamp.invalid}")
	Date timestamp;
	
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
		this.setNormName(name.toLowerCase());
	}

	public String getNormName() {
		return normName;
	}

	public void setNormName(String normName) {
		this.normName = normName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCd() {
		return zipCd;
	}

	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}

	public String getStateCd() {
		return stateCd;
	}

	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public BigDecimal getGcLat() {
		return gcLat;
	}

	public void setGcLat(BigDecimal gcLat) {
		this.gcLat = gcLat;
	}

	public BigDecimal getGcLng() {
		return gcLng;
	}

	public void setGcLng(BigDecimal gcLng) {
		this.gcLng = gcLng;
	}

	public String getParkingTypeCd() {
		return parkingTypeCd;
	}

	public void setParkingTypeCd(String parkingTypeCd) {
		this.parkingTypeCd = parkingTypeCd;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public long getDefaultRate() {
		return defaultRate;
	}

	public void setDefaultRate(long defaultRate) {
		this.defaultRate = defaultRate;
	}

	public boolean isManned() {
		return manned;
	}

	public void setManned(boolean manned) {
		this.manned = manned;
	}

	public String getMannedDesc() {
		return mannedDesc;
	}

	public void setMannedDesc(String mannedDesc) {
		this.mannedDesc = mannedDesc;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	
	
	
}
