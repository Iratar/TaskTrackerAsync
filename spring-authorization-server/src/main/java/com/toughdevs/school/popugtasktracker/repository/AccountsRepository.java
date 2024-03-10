package com.toughdevs.school.popugtasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toughdevs.school.popugtasktracker.repository.model.AccountEntity;

@Repository
public interface AccountsRepository extends JpaRepository<AccountEntity, Long> {

	AccountEntity findByPublicId(String publicId);

	AccountEntity findByEmail(String email);

//	@Query("SELECT b FROM Book b WHERE b.publishDate > :date")
//	List<AccountEntity> findByPublishedDateAfter(@Param("date") LocalDate date);

}
