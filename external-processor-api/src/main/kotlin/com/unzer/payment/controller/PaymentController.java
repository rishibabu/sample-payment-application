package com.unzer.payment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.unzer.payment.dto.PaymentRequestDto;
import com.unzer.payment.dto.PaymentResponseDto;
import com.unzer.payment.service.PaymentService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class PaymentController {

	private final PaymentService paymentService;

	/**
	 * Using the constructor dependency injection
	 * 
	 * @param paymentService
	 */
	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;

	}

	/**
	 * This method will create the payment
	 * 
	 * @param transaction
	 * @return
	 */

	@PostMapping(value = "v1/payment/create")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Mono<PaymentResponseDto>> createPayment(@Valid @RequestBody PaymentRequestDto transaction) {

		Mono<PaymentResponseDto> payment = null;

		payment = paymentService.createPayment(transaction);

		HttpStatus status = (payment != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(payment, status);

	}

	/**
	 * This method will cancel the payment
	 * 
	 * @param id
	 * @return
	 */

	@PostMapping(value = "v1/payment/{id}/cancel")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Mono<PaymentResponseDto>> cancelPayment(@PathVariable Long id) {

		Mono<PaymentResponseDto> paymentCancelResult = paymentService.cancelPayment(id);
		HttpStatus status = (paymentCancelResult != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(paymentCancelResult, status);

	}

	/**
	 * This method will fetch a payment by its id
	 * 
	 * @param id
	 * @return
	 */

	@GetMapping(value = "v1/payment/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PaymentResponseDto> getPayment(@PathVariable Long id) {

		PaymentResponseDto paymentbyId = paymentService.getPayment(id);
		HttpStatus status = (paymentbyId != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(paymentbyId, status);

	}

}
