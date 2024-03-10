package com.toughdevs.school.popugtasktracker.tasks.kafka;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class ProducerTasksCompleted {

  private static final Logger logger = LoggerFactory.getLogger(ProducerTasksCompleted.class);
  private static final String TOPIC = "tasks.completed";

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String key, String value) {
	  CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, key, value);
		future.whenComplete((result, ex) -> {
			if (ex == null) {
				logger.info("TOPIC: {}, Sent message=[{}] with offset=[{}]", TOPIC, key, result.getRecordMetadata().offset());
			} else {
				logger.info("TOPIC: {}, Unable to send message=[{}] due to : {}", TOPIC, key, ex.getMessage());
			}
		});
  }
}
