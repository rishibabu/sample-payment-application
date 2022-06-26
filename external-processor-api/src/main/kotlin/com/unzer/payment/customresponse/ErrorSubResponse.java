package com.unzer.payment.customresponse;

import com.unzer.payment.constant.MessageCode;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ErrorSubResponse {

	MessageCode code;
	String text;

}
