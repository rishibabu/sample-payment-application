package com.unzer.payment.customeexception;

import com.unzer.payment.constant.MessageCode;

public class PaymentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6367386210667630807L;

	private final MessageCode code;
	private final String error;

	public PaymentNotFoundException(MessageCode code, String error) {
		super();
		this.code = code;
		this.error = error;
	}

	public MessageCode getCode() {
		return code;
	}

	public String getError() {
		return error;
	}

}
