package com.toughdevs.school.popugtasktracker.web.domain;

import java.io.Serializable;
import java.util.UUID;

public class UpdateAccountRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private UUID publicId;
	private String email;
	private String fullName;
	private boolean active;
	private RoleEnum role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getPublicId() {
		return publicId;
	}

	public void setPublicId(UUID publicId) {
		this.publicId = publicId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UpdateAccountRequest [id=" + id + ", publicId=" + publicId + ", email=" + email + ", fullName="
				+ fullName + ", active=" + active + ", role=" + role + "]";
	}

}
