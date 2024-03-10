package com.toughdevs.school.popugtasktracker.accounting.service.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class TaskData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String publicId;
	private String description;
	private Account assignedTo;
	private BigDecimal costAssign;
	private BigDecimal costDone;
}
