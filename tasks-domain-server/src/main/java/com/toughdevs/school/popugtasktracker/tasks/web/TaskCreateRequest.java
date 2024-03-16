package com.toughdevs.school.popugtasktracker.tasks.web;

import java.io.Serializable;

import lombok.Data;

@Data
public class TaskCreateRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;

}
