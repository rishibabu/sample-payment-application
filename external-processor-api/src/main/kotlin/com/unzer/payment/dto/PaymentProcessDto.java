package com.unzer.payment.dto;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentProcessDto {
	@NotNull
	private String approvalCode;

	@Builder
	private PaymentProcessDto(String approvalCode) {
		super();
		this.approvalCode = approvalCode;
	}

}
