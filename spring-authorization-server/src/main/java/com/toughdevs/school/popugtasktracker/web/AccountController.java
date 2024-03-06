package com.toughdevs.school.popugtasktracker.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toughdevs.school.popugtasktracker.service.AccountsService;
import com.toughdevs.school.popugtasktracker.web.domain.Account;
import com.toughdevs.school.popugtasktracker.web.domain.RegisterAccountRequest;
import com.toughdevs.school.popugtasktracker.web.domain.UpdateAccountRequest;

@RestController
public class AccountController {
	
	Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountsService accountsService;

	@PostMapping(path = "/registerAccount", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public Account registerAccount(RegisterAccountRequest request) {
		logger.info("registerAccount: {}", request);
		
		Account account = accountsService.registerAccount(request);
		
		logger.info("Account registered: {}", account);
		return account;
	}
	
	@PostMapping(path = "/updateAccount", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public Account updateAccount(UpdateAccountRequest request) throws Exception {
		logger.info("updateAccount: {}", request);
		
		Account account = accountsService.updateAccount(request);
		
		logger.info("Account updated: {}", account);
		return account;
	}
	
	@PostMapping(path = "/deleteAccount", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public Account deleteAccount(String publicId) throws Exception {
		logger.info("deleteAccount: {}", publicId);
		
		Account account = accountsService.deleteAccount(publicId);
		
		logger.info("Account deleted: {}", account);
		return account;
	}
	
	@GetMapping(path = "/accounts")
	public List<Account> getAccounts() throws Exception {
		logger.info("getAccounts");
		
		List<Account> accounts = accountsService.findAll();
		
		logger.info("Account retreived: {}", accounts);
		return accounts;
	}
}
