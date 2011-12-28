package com.leopin.parkfifty.shared.domain;

import java.util.Date;

import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindexed;

@Entity
@Unindexed
public class Rate {

	@Id
	private Long Id;

	@Parent
	@Indexed
	private Key<Location> locationKey;
	
	private Long rate;
	
	private DurationType rateType;
	
	@Indexed
	private Date fromDate;
	
	@Indexed
	private Date toDate;
	
	private int noFeeDuration;
	
	private DurationType noFeeDurationType;
	
	@Indexed
	private String updateUid;
	
	private Date updateTs;
	
	private Date deleteTs;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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

	public Long getRate() {
		return rate;
	}

	public void setRate(Long rate) {
		this.rate = rate;
	}

	public DurationType getRateType() {
		return rateType;
	}

	public void setRateType(DurationType rateType) {
		this.rateType = rateType;
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

	public int getNoFeeDuration() {
		return noFeeDuration;
	}

	public void setNoFeeDuration(int noFeeDuration) {
		this.noFeeDuration = noFeeDuration;
	}

	public DurationType getNoFeeDurationType() {
		return noFeeDurationType;
	}

	public void setNoFeeDurationType(DurationType noFeeDurationType) {
		this.noFeeDurationType = noFeeDurationType;
	}

	public String getUpdateUid() {
		return updateUid;
	}

	public void setUpdateUid(String updateUid) {
		this.updateUid = updateUid;
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
	
}
