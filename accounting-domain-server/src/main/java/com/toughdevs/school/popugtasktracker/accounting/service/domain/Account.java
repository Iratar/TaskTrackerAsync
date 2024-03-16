package com.toughdevs.school.popugtasktracker.accounting.service.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String publicId;
	private BigDecimal balance;
	private String role;
}
