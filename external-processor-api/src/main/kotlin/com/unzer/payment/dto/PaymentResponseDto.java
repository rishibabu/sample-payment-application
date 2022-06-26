package com.unzer.payment.dto;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class PaymentResponseDto {
	@NotNull
	private String approvalCode;
	@NotNull
	private String paymentStatus;

	@Builder
	private PaymentResponseDto(Long id, String approvalCode, String paymentStatus) {
		super();
		this.approvalCode = approvalCode;
		this.paymentStatus = paymentStatus;
	}

}
