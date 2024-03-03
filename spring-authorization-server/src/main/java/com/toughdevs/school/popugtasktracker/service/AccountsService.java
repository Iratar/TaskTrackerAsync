package com.toughdevs.school.popugtasktracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toughdevs.school.popugtasktracker.repository.AccountsRepository;
import com.toughdevs.school.popugtasktracker.repository.model.AccountEntity;
import com.toughdevs.school.popugtasktracker.web.domain.Account;
import com.toughdevs.school.popugtasktracker.web.domain.RegisterAccountRequest;
import com.toughdevs.school.popugtasktracker.web.domain.RoleEnum;
import com.toughdevs.school.popugtasktracker.web.domain.UpdateAccountRequest;

@Service
public class AccountsService {
	Logger logger = LoggerFactory.getLogger(AccountsService.class);

	@Autowired
	private AccountsRepository accountsRepository;

	public Account registerAccount(RegisterAccountRequest request) {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setPublicId(UUID.randomUUID().toString());
		accountEntity.setRole(RoleEnum.EMPLOYEE.name());
		accountEntity.setActive(true);
		accountEntity.setFullName(request.getFullName());
		accountEntity.setEmail(request.getEmail());
		accountEntity.setPassword(request.getPassword());
		
		AccountEntity acc = accountsRepository.saveAndFlush(accountEntity);
		return accountFunctionFromDBtoRest(acc);
	}

	public Account updateAccount(UpdateAccountRequest request) throws Exception {

		Optional<AccountEntity> entity = accountsRepository.findById(request.getId());
		if (!entity.isPresent()) {
			throw new Exception("does not exists");
		}
		AccountEntity accountEntity = entity.get();
		accountEntity.setRole(request.getRole().name());
		accountEntity.setActive(true);
		accountEntity.setFullName(request.getFullName());
		accountEntity.setEmail(request.getEmail());
		
		AccountEntity acc = accountsRepository.saveAndFlush(accountEntity);
		return accountFunctionFromDBtoRest(acc);
	}

	private Account accountFunctionFromDBtoRest(AccountEntity acc) {
		Account accountResp = new Account();
		accountResp.setId(acc.getId());
		accountResp.setPublicId(acc.getPublicId());
		accountResp.setRole(RoleEnum.valueOf(acc.getRole()));
		accountResp.setActive(acc.isActive());
		accountResp.setFullName(acc.getFullName());
		accountResp.setEmail(acc.getEmail());
		return accountResp;
	}

	public Account deleteAccount(String publicId) {
		AccountEntity acc = accountsRepository.findByPublicId(publicId);
		accountsRepository.deleteById(acc.getId());
		return accountFunctionFromDBtoRest(acc);
	}

	public List<Account> findAll() {
		List<AccountEntity> list = accountsRepository.findAll();
		return list.stream().map(a -> accountFunctionFromDBtoRest(a)).toList();
	}

	public Account findById(Long id) {
		Optional<AccountEntity> accOptional = accountsRepository.findById(id);
		if (accOptional.isPresent()) {
			return accountFunctionFromDBtoRest(accOptional.get());
		}
		return null;
	}

}
