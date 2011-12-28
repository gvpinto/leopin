package com.leopin.parkfifty.shared.domain;

public enum ImageType {
	PNG("png"), GIF("gif"), JPG("jpg"), JPEG("jpeg");
	
	String imageType;
	
	private ImageType(String imageType) {
		this.imageType = imageType;
	}
	
	public String imageType() {
		return imageType;
	}
}
