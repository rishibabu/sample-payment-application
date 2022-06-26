package com.unzer.payment.service;

import com.unzer.payment.dto.PaymentRequestDto;
import com.unzer.payment.dto.PaymentResponseDto;

import reactor.core.publisher.Mono;

public interface PaymentService {
	
	public  Mono<PaymentResponseDto> createPayment(PaymentRequestDto transaction);
	
	public  Mono<PaymentResponseDto> cancelPayment(Long paymentId);
	
	public  PaymentResponseDto getPayment(Long paymentId);


}
