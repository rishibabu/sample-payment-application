package com.unzer.payment.customresponse;


import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Builder
@Value
@ToString
public class ApiResponse<T> {
	
	Boolean status;
	ErrorSubResponse error;
	MessageSubResponse message;
	T data;

}