package com.leopin.parkfifty.shared.domain;

import java.util.Date;

import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindexed;
import com.leopin.parkfifty.shared.utils.ApplicationVersion;


@Entity
@Unindexed
public class Discount {
	
	private static final long serialVersionUID = ApplicationVersion.SERIAL_VERSION_UID;
	
	@Id
	private Long id;
	
	@Parent
	@Indexed
	@JsonIgnore
	private Key<Location> locationKey;
	
	private String couponCode;
	
	private String couponDesc;
	
	private long value;
	
	// AMT:$ - Dollars, PCT:%-Percentage
	private ValueType valueType;
	
	@Indexed
	Date fromDate;
	
	@Indexed
	Date toDate;
	
	Date updateTs;
	
	Date deleteTs;
	
	@Indexed
	String updateUid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public Key<Location> getLocationKey() {
		return locationKey;
	}

	@JsonIgnore
	public void setLocationKey(Key<Location> locationKey) {
		this.locationKey = locationKey;
	}

	@JsonIgnore
	public void setLocationKeyUsingId(Long id) {
		if (id > 0L) {
			this.locationKey = new Key<Location>(Location.class, id);
		}
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponDesc() {
		return couponDesc;
	}

	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public ValueType getValueType() {
		return valueType;
	}

	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
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
	
	
}
