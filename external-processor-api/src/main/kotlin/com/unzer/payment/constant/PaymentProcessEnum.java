package com.unzer.payment.constant;

public enum PaymentProcessEnum {

	APPROVED("approved"), 
	CANCELLED("cancelled");

	private final String text;

	PaymentProcessEnum(String text) {

		this.text = text;
	}

	public String getText() {
		return text;
	}

}
