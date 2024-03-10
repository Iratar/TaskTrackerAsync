package com.toughdevs.school.popugtasktracker.accounting.kafka.listeners.tasks;

import org.apache.kafka.common.Uuid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.toughdevs.school.popugtasktracker.accounting.repository.tasks.TasksRepository;
import com.toughdevs.school.popugtasktracker.accounting.repository.tasks.model.TaskEntity;
import com.toughdevs.school.popugtasktracker.accounting.repository.transactions.TransactionsRepository;
import com.toughdevs.school.popugtasktracker.accounting.repository.transactions.model.TransactionEntity;

@Service
public class ConsumerTasksCompleted {

	private final Logger logger = LoggerFactory.getLogger(ConsumerTasksCompleted.class);

	@Autowired
	private TasksRepository tasksRepository;
	@Autowired
	private TransactionsRepository transactionsRepository;

	@KafkaListener(topics = "tasks.completed", groupId = "group_be_tasks")
	public void listen(String value, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
			@Header(KafkaHeaders.RECEIVED_KEY) String key) throws JsonMappingException, JsonProcessingException {
		logger.info(String.format("\n\n Consumed event from topic %s: key = %-10s value = %s \n\n", topic, key, value));

		try {
			TaskEntity taskEntity = tasksRepository.findByPublicId(value);
			if (taskEntity == null) {
				taskEntity = new TaskEntity();
				taskEntity.setPublicId(value);
				tasksRepository.saveAndFlush(taskEntity);
			}
			TransactionEntity transactionEntity = new TransactionEntity();
			transactionEntity.setPublicId(Uuid.randomUuid().toString());
			transactionEntity.setAccountId(taskEntity.getAssignedTo());
			transactionEntity.setTaskId(taskEntity.getId());
			transactionEntity.setAmount(taskEntity.getCostDone());
			transactionEntity.setPaymentType("TRANSACTION");
			transactionEntity.setType("DEBIT");
			transactionsRepository.saveAndFlush(transactionEntity);
			
			// TODO: PRODUCE TRANSACTIONS.APPLIED EVENT
		} catch (Exception ex) {
			logger.error("incorrect message recieved: {}, {}", key, value);
		}
	}
}
