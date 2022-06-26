package com.unzer.payment.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PaymentRequestDto {
    @NotNull
	private String cardNumber;
    @NotNull
	private String cardExpiryDate;
    @NotNull
	private String cardCvc;
    @NotNull
	private String amount;
    @NotNull
	private String currency;

}
