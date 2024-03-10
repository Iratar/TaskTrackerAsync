package com.toughdevs.school.popugtasktracker.accounting.repository.tasks.model;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "TASKS")
public class TaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "public_id")
	private String publicId;

	@Column(name = "description")
	private String description;

	@Column(name = "cost_assign")
	private BigDecimal costAssign;

	@Column(name = "cost_done")
	private BigDecimal costDone;

	@Column(name = "assigned_to")
	private Long assignedTo;

	public TaskEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Long assignedTo) {
		this.assignedTo = assignedTo;
	}

	@Override
	public String toString() {
		return "TaskEntity [id=" + id + ", publicId=" + publicId + ", description=" + description + ", costAssign="
				+ costAssign + ", costDone=" + costDone + ", assignedTo=" + assignedTo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskEntity other = (TaskEntity) obj;
		return Objects.equals(id, other.id);
	}

	public BigDecimal getCostAssign() {
		return costAssign;
	}

	public void setCostAssign(BigDecimal costAssign) {
		this.costAssign = costAssign;
	}

	public BigDecimal getCostDone() {
		return costDone;
	}

	public void setCostDone(BigDecimal costDone) {
		this.costDone = costDone;
	};

}
