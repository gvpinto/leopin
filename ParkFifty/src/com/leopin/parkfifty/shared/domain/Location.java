package com.leopin.parkfifty.shared.domain;

import static com.leopin.parkfifty.shared.AppRegExp.CITY;
import static com.leopin.parkfifty.shared.AppRegExp.COUNTRY_CD;
import static com.leopin.parkfifty.shared.AppRegExp.EMAIL;
import static com.leopin.parkfifty.shared.AppRegExp.EMPTY_STRING;
import static com.leopin.parkfifty.shared.AppRegExp.GEO_CD;
import static com.leopin.parkfifty.shared.AppRegExp.LOCATION_DESC;
import static com.leopin.parkfifty.shared.AppRegExp.LOCATION_NAME;
import static com.leopin.parkfifty.shared.AppRegExp.PARK_FACILITY_TYPE;
import static com.leopin.parkfifty.shared.AppRegExp.PHONE_NUM;
import static com.leopin.parkfifty.shared.AppRegExp.SIMPLE_DESC;
import static com.leopin.parkfifty.shared.AppRegExp.STATE_CD;
import static com.leopin.parkfifty.shared.AppRegExp.STREET;
import static com.leopin.parkfifty.shared.AppRegExp.ZIP_CD;

import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindexed;
import com.leopin.parkfifty.server.utils.Utils;

/**
 * The domain object defines the location of the parking place
 * @author Glenn Pinto
 */
@Entity
@Unindexed
public class Location {
	
	public Location() {
		this.timestamp = new Date();
	}
	
	@Id Long id;

	/**
	 * Reference to the Company Entity
	 */
	@JsonIgnore
	@Parent
	private Key<Company> company;
	
	@Indexed
	private String normName;
	
	@NotNull(message="{com.leopin.contraints.location.name.invalid}")
	@Pattern(regexp=LOCATION_NAME, message="{com.leopin.contraints.location.name.invalid}")		
	private String name;

	/**
	 * Location Description
	 */
	@NotNull(message="{com.leopin.contraints.location.desc.invalid}")
	@Pattern(regexp=LOCATION_DESC, message="{com.leopin.contraints.location.desc.invalid}")	
	private String description;
		
	@NotNull(message="{com.leopin.contraints.street.invalid}")
	@Pattern(regexp=STREET, message="{com.leopin.contraints.street.invalid}")			
	private String street;

	@NotNull(message="{com.leopin.contraints.street.invalid}")
	@Pattern(regexp=STREET + "|" + EMPTY_STRING, message="{com.leopin.contraints.street.invalid}")			
	private String street2;

	@NotNull(message="{com.leopin.contraints.city.invalid}")
	@Pattern(regexp=CITY, message="{com.leopin.contraints.city.invalid}")			
	private String city;
	
	@NotNull(message="{com.leopin.contraints.zipCd.invalid}")
	@Pattern(regexp=ZIP_CD, message="{com.leopin.contraints.zipCd.invalid}")				
	private String zipCd;
	
	@NotNull(message="{com.leopin.contraints.stateCd.invalid}")
	@Pattern(regexp=STATE_CD, message="{com.leopin.contraints.stateCd.invalid}")					
	private String stateCd;
	
	@NotNull(message="{com.leopin.contraints.countryCd.invalid}")
	@Pattern(regexp=COUNTRY_CD, message="{com.leopin.contraints.countryCd.invalid}")						
	private String countryCd;
	
	@NotNull(message="{com.leopin.contraints.gc.latitude.invalid}")
	@Pattern(regexp=GEO_CD, message="{com.leopin.contraints.gc.latitude.invalid}")		
	private String gcLat;
	
	@NotNull(message="{com.leopin.contraints.gc.longitude.invalid}")
	@Pattern(regexp=GEO_CD, message="{com.leopin.contraints.gc.longitude.invalid}")
	private String gcLng;
	
	// The type of parking C -> Covered, O -> Open, S -> Street CM -> covered and multi layered
	@NotNull(message="{com.leopin.contraints.parking.type.code.invalid}")
	@Pattern(regexp=PARK_FACILITY_TYPE, message="{com.leopin.contraints.gc.latitude.invalid}")	
	private String parkingTypeCd;
	
	@NotNull(message="{com.leopin.contraints.primary.phone.invalid}")
	@Pattern(regexp=PHONE_NUM, message="{com.leopin.contraints.primary.phone.invalid}")
	private String priPhone;
	
	@NotNull(message="{com.leopin.contraints.secondary.phone.invalid}")
	@Pattern(regexp=PHONE_NUM + "|" + EMPTY_STRING, message="{com.leopin.contraints.secondary.phone.invalid}")
	private String secPhone;
	
	@NotNull(message="{com.leopin.contraints.fax.invalid}")
	@Pattern(regexp=PHONE_NUM + "|" + EMPTY_STRING, message="{com.leopin.contraints.fax.invalid}")
	private String fax;
	
	@NotNull(message="{com.leopin.contraints.email.invalid}")
	@Pattern(regexp=EMAIL, message="{com.leopin.contraints.email.invalid}")
	private String email;
	
	/**
	 * Total number of parking spaces
	 */
	@Min(value=1, message="{com.leopin.constraints.minvalue.invalid}")
	private int totalCapacity;
	
	/**
	 * Default Rate in Cents per/hr
	 */
	private long defaultRate;
	
	/**
	 * Is this a manned facility
	 */
	private boolean manned;
	
	/**
	 * Description for the manned facility
	 */
	@NotNull(message="{com.leopin.contraints.manned.desc.invalid}")
	@Pattern(regexp=SIMPLE_DESC, message="{com.leopin.contraints.manned.desc.invalid}")
	private String mannedDesc;
	
	@NotNull(message="{com.leopin.contraints.timestamp.invalid}")
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
		this.stateCd = Strings.nullToEmpty(stateCd).toUpperCase();
	}

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = Strings.nullToEmpty(countryCd).toUpperCase();
	}

	public String getGcLat() {
		return gcLat;
	}

	public void setGcLat(String gcLat) {
		this.gcLat = gcLat;
	}

	public String getGcLng() {
		return gcLng;
	}

	public void setGcLng(String gcLng) {
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

	@Override
	public boolean equals(Object object) {
		
		if (object instanceof Location) {
			Location that = (Location) object;
			return Objects.equal(this.name, that.name) 
					&& Objects.equal(this.company, that.company);
		}
		
		return false;
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.name, this.company);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("Id", this.id)
				.add("Name", this.name)
				.add("Description", this.description)
				.add("Street", this.street)
				.add("Street2", this.street2)
				.add("City", this.city)
				.add("Zip", this.zipCd)
				.add("State", this.stateCd)
				.add("Country", this.countryCd)
				.add("GeoCode Lat", this.gcLat)
				.add("GeoCode Lng", this.gcLng)
				.add("Parking Type Code", this.parkingTypeCd)
				.add("Pri Phone", this.priPhone)
				.add("Sec Phone", this.secPhone)
				.add("Fax", this.fax)
				.add("Email", this.email)
				.add("Total Capacity", this.totalCapacity)
				.add("Default Rate", this.defaultRate)
				.add("Is Manned", this.manned)
				.add("Manned Desc", this.mannedDesc)
				.add("Timestamp", this.timestamp)
				.toString();
	}
	
	
}
