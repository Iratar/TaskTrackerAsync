package com.toughdevs.school.popugtasktracker.accounting.kafka.listeners.accounts;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.toughdevs.school.popugtasktracker.accounting.repository.accounts.AccountsRepository;
import com.toughdevs.school.popugtasktracker.accounting.repository.accounts.model.AccountEntity;

@Service
public class ConsumerAccounts {

	private final Logger logger = LoggerFactory.getLogger(ConsumerAccounts.class);

	@Autowired
	private AccountsRepository accountsRepository;

	@KafkaListener(topics = "accounts", groupId = "accounting_group_be_accounts")
	public void listen(String value, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
			@Header(KafkaHeaders.RECEIVED_KEY) String key) throws JsonMappingException, JsonProcessingException {
		logger.info(String.format("\n\n Consumed event from topic %s: key = %-10s value = %s \n\n", topic, key, value));

		try {
			AccountEntity accountEntity = accountsRepository.findByPublicId(key);
			if (accountEntity == null) {
				accountEntity = new AccountEntity();
				accountEntity.setPublicId(key);
				accountEntity.setBalance(BigDecimal.ZERO);
			}
			accountEntity.setRole(value);
			accountsRepository.saveAndFlush(accountEntity);
		} catch (Exception ex) {
			logger.error("incorrect message recieved: {}, {}", key, value);
		}
	}
}
