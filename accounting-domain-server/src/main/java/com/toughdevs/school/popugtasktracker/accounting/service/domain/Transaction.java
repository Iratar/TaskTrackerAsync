package com.toughdevs.school.popugtasktracker.accounting.service.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String publicId;
	private Long accountId;
	private Long taskId;
	private BigDecimal amount;
	private String type; // DEBIT or CREDIT
	private String paymentType; // Payemnt type or not
}
