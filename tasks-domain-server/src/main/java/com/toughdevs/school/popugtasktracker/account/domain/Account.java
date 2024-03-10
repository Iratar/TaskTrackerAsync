package com.toughdevs.school.popugtasktracker.account.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String publicId;
	private String fullName;
	private String role;
}
