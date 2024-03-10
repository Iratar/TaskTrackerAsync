package com.toughdevs.school.popugtasktracker.web.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class TaskData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String publicId;
	private String description;
	private Account assignedTo;
	private String status;
}