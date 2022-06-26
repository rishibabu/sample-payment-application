package com.unzer.payment.constant;

public enum MessageCode {

	ERR_REQUEST_BODY_VALID("Please input all the fields in the request body"),

	ERR_MESSAGE_NOT_ALLOWED("The method requested is not allowed,please use the appropriate http method"),

	ERR_GENERAL_ERROR("The System has encountered an error"),

	ERR_RESOURCE_NOT_FOUND("The payment resource doesn't exist for id requested");

	private String error;

	MessageCode(String error) {

		this.error = error;

	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

}
