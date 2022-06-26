package com.unzer.payment.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.unzer.payment.model.Payment;

public interface ReactivePaymentRepository extends CrudRepository<Payment, Long> {

	Payment save(Optional<Payment> payment);


}
