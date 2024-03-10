package com.toughdevs.school.popugtasktracker.auth.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class ConsumerAccountsStream {

	private final Logger logger = LoggerFactory.getLogger(ConsumerAccountsStream.class);

	  @KafkaListener(topics = "accounts-stream", groupId = "group_id")
	  public void listen(String value,
	    @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
	    @Header(KafkaHeaders.RECEIVED_KEY) String key) {
	    logger.info(String.format("\n\n Consumed event from topic %s: key = %-10s value = %s \n\n", topic, key, value));
	  }
	}

