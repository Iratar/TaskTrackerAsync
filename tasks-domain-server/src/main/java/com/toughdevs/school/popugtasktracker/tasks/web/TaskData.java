package com.toughdevs.school.popugtasktracker.tasks.web;

import java.io.Serializable;

import com.toughdevs.school.popugtasktracker.tasks.account.domain.Account;

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
	private String status;
}
