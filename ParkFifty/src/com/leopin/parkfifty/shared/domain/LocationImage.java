package com.leopin.parkfifty.shared.domain;

import java.util.Date;

import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindexed;
import com.leopin.parkfifty.shared.ApplicationVersion;

@Entity
@Unindexed
public class LocationImage {

	private static final long serialVersionUID = ApplicationVersion.SERIAL_VERSION_UID;
	
	@Id
	private Long id;
	
	@Parent
	@Indexed
	@JsonIgnore
	private Key<Location> locationKey;
	
	private byte[] image;
	
	// gif, png, jpg, jpeg etc.
	private ImageType imageType;
	
	private Date updateTs;

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
	public void setLocationKey(Long id) {
		if (id > 0L) {
			this.locationKey = new Key<Location>(Location.class, id);
		}
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public ImageType getImageType() {
		return imageType;
	}

	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}

	public Date getUpdateTs() {
		return updateTs;
	}

	public void setUpdateTs(Date updateTs) {
		this.updateTs = updateTs;
	}
	
	
}
