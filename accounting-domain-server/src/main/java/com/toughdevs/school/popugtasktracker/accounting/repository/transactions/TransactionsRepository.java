package com.toughdevs.school.popugtasktracker.accounting.repository.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toughdevs.school.popugtasktracker.accounting.repository.transactions.model.TransactionEntity;

@Repository
public interface TransactionsRepository extends JpaRepository<TransactionEntity, Long> {
	
	TransactionEntity findByPublicId(String publicId);
}
