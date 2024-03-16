package com.toughdevs.school.popugtasktracker.accounting.repository.accounts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toughdevs.school.popugtasktracker.accounting.repository.accounts.model.AccountEntity;

@Repository
public interface AccountsRepository extends JpaRepository<AccountEntity, Long> {
	
	List<AccountEntity> findByRole(String role);
	AccountEntity findByPublicId(String publicId);
}
