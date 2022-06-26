package com.unzer.payment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String approvalCode;

	private String paymentStatus;

	@Builder
	public Payment(Long id, String approvalCode, String paymentStatus) {
		super();
		this.id = id;
		this.approvalCode = approvalCode;
		this.paymentStatus = paymentStatus;
	}

}
