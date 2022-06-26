package com.unzer.payment.service.impl;

import static com.unzer.payment.constant.PaymentConstants.PAYMENT_CANCEL_API_URL;
import static com.unzer.payment.constant.PaymentConstants.PAYMENT_PROCESSING_API_URL;
import static com.unzer.payment.constant.PaymentProcessEnum.APPROVED;

import java.util.Optional;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.unzer.payment.constant.PaymentProcessEnum;
import com.unzer.payment.customeexception.PaymentNotFoundException;
import com.unzer.payment.dto.PaymentProcessDto;
import com.unzer.payment.dto.PaymentRequestDto;
import com.unzer.payment.dto.PaymentResponseDto;
import com.unzer.payment.model.Payment;
import com.unzer.payment.repository.ReactivePaymentRepository;
import com.unzer.payment.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 
 * @author Rishi This service class consists of all the business logic for the
 *         payment Implemented the reactive approach by using the webclient non
 *         blockling way of calling an external API instead of blocking Rest
 *         Template
 */
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	WebClient client;

	private final ReactivePaymentRepository paymentRepo;

	@Autowired
	public PaymentServiceImpl(ReactivePaymentRepository paymentRepo) {
		this.paymentRepo = paymentRepo;
	}

	@Override
	public Mono<PaymentResponseDto> createPayment(PaymentRequestDto transaction) {
		Mono<PaymentProcessDto> response = client.post().uri(PAYMENT_PROCESSING_API_URL)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(transaction), PaymentRequestDto.class).retrieve().bodyToMono(PaymentProcessDto.class);

		Mono<Payment> paymentResponsefromApi = response.flatMap(res -> {
			return Mono.just(
					Payment.builder().approvalCode(res.getApprovalCode()).paymentStatus(APPROVED.getText()).build());
		});

		Payment paymentResponsefromDb = paymentRepo.save(paymentResponsefromApi.block());

		return Mono.just(PaymentResponseDto.builder().approvalCode(paymentResponsefromDb.getApprovalCode())
				.paymentStatus(paymentResponsefromDb.getPaymentStatus()).build());
	}

	@Override
	public Mono<PaymentResponseDto> cancelPayment(Long paymentId) {

		Optional<Payment> payment = paymentRepo.findById(paymentId);

		PaymentProcessDto processDto = new PaymentProcessDto();

		payment.ifPresent(paymentResult -> {
			log.info("setting the approval code from the payment result" + payment);
			processDto.setApprovalCode(paymentResult.getApprovalCode());
		});

		if (payment.isEmpty()) {

			log.info("There is no payment resource available for the id "+ paymentId);
			throw new PaymentNotFoundException(null,null);
		}

		Mono<ResponseEntity<HttpStatus>> response = client.post()
				.uri(PAYMENT_CANCEL_API_URL.replace("approvalCode", processDto.getApprovalCode()))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(processDto), PaymentProcessDto.class).retrieve().toEntity(HttpStatus.class);

		Mono<Payment> paymentUpdated = response.flatMap(res -> {

			if (res.getStatusCode().equals(HttpStatus.OK)) {
				payment.ifPresentOrElse(paymentResult -> {
					paymentResult.setPaymentStatus(PaymentProcessEnum.CANCELLED.getText());
				}, () -> new PaymentNotFoundException(null, null));

				log.info("updating the payment status to " + payment.get().getPaymentStatus());

				return Mono.just(paymentRepo.save(payment.get()));
			} else {

				return Mono.empty();
			}

		});

		return paymentUpdated.flatMap(res -> {
			return Mono.just(PaymentResponseDto.builder().approvalCode(res.getApprovalCode())
					.paymentStatus(res.getPaymentStatus()).build());
		});

	}

	@Override
	public PaymentResponseDto getPayment(Long paymentId) {

		Optional<Payment> payment = paymentRepo.findById(paymentId);

		PaymentResponseDto responseDto = new PaymentResponseDto();

		payment.ifPresentOrElse(paymentResult -> {
			responseDto.setPaymentStatus(paymentResult.getPaymentStatus());
		}, Exception::new);

		return responseDto;

	}

}
