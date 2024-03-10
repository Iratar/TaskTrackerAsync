package com.toughdevs.school.popugtasktracker.web;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.toughdevs.school.popugtasktracker.service.AccountsService;
import com.toughdevs.school.popugtasktracker.web.domain.Account;
import com.toughdevs.school.popugtasktracker.web.domain.RegisterAccountRequest;
import com.toughdevs.school.popugtasktracker.web.domain.UpdateAccountRequest;

@Controller
public class AccountController {
	
	Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountsService accountsService;

	@PostMapping(path = "/registerAccount", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public String registerAccount(RegisterAccountRequest request) throws IOException {
		logger.info("registerAccount: {}", request);
		
		Account account = accountsService.registerAccount(request);
		
		logger.info("Account registered: {}", account);
		
		return "index";
	}
	
	@PostMapping(path = "/updateAccount", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public String updateAccount(UpdateAccountRequest request, Model model) throws Exception {
		logger.info("updateAccount: {}", request);
		
		Account account = accountsService.updateAccount(request);
		
		logger.info("Account updated: {}", account);
		
		List<Account> accounts = accountsService.findAll();
		model.addAttribute("accounts", accounts);
		return "accountList";
	}
	
	@PostMapping(path = "/deleteAccount", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public Account deleteAccount(String publicId) {
		logger.info("deleteAccount: {}", publicId);
		
		Account account = accountsService.deleteAccount(publicId);
		
		logger.info("Account deleted: {}", account);
		return account;
	}
	
	@GetMapping(path = "/accounts/list")
	public String getAccounts(Model model) {
		logger.info("getAccounts");
		
		List<Account> accounts = accountsService.findAll();
		model.addAttribute("accounts", accounts);
		
		logger.info("Account retreived: {}", accounts);
		return "accountList";
	}
	
	@GetMapping(path = "/accounts/edit/{accountId}")
	public String getEditAccounts(@PathVariable("accountId") Long accountId, Model model) {
		logger.info("getAccount by ID: {}", accountId);
		
		Account account = accountsService.findById(accountId);
		model.addAttribute("account", account);
		
		logger.info("Account retreived: {}", account);
		return "editAccount";
	}
	
}
