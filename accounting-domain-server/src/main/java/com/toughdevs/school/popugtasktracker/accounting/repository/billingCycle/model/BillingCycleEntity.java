package com.toughdevs.school.popugtasktracker.accounting.repository.billingCycle.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "BILLING_CYCLE")
public class BillingCycleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "public_id")
	private String publicId;

	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "cycle_date")
	private Date cycleDate;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "status")
	private String status;

	public BillingCycleEntity() {
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

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Date getCycleDate() {
		return cycleDate;
	}

	public void setCycleDate(Date cycleDate) {
		this.cycleDate = cycleDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BillingCycleEntity [id=" + id + ", publicId=" + publicId + ", accountId=" + accountId + ", cycleDate="
				+ cycleDate + ", amount=" + amount + ", status=" + status + "]";
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
		BillingCycleEntity other = (BillingCycleEntity) obj;
		return Objects.equals(id, other.id);
	};

}
