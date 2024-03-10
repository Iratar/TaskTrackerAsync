package com.toughdevs.school.popugtasktracker.auth.kafka;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.toughdevs.school.popugtasktracker.web.domain.Account;

@Service
public class ProducerAccountsStream {

	private static final Logger logger = LoggerFactory.getLogger(ProducerAccountsStream.class);
	private static final String TOPIC = "accounts-stream";

	@Autowired
	private KafkaTemplate<String, Account> kafkaTemplate;

	public void sendMessage(String key, Account value) {
		CompletableFuture<SendResult<String, Account>> future = kafkaTemplate.send(TOPIC, key, value);
		future.whenComplete((result, ex) -> {
			if (ex == null) {
				logger.info("TOPIC: {}, Sent message=[{}] with offset=[{}]", TOPIC, key, result.getRecordMetadata().offset());
			} else {
				logger.info("TOPIC: {}, Unable to send message=[{}] due to : {}", TOPIC, key, ex.getMessage());
			}
		});
	}
}
