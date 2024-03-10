package com.toughdevs.school.popugtasktracker.accounting.repository.billingCycle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toughdevs.school.popugtasktracker.accounting.repository.billingCycle.model.BillingCycleEntity;

@Repository
public interface BillingCycleRepository extends JpaRepository<BillingCycleEntity, Long> {
	
	BillingCycleEntity findByPublicId(String publicId);
}
