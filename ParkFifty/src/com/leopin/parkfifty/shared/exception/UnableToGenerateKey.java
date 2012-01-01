package com.leopin.parkfifty.shared.exception;

public class UnableToGenerateKey extends RuntimeException {
	public UnableToGenerateKey() {
		this("Unable to Generate Key");
	}
	
	public UnableToGenerateKey(String message) {
		super(message);
	}
}
