package com.toughdevs.school.popugtasktracker.accounting.kafka.listeners.tasks;

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
import com.toughdevs.school.popugtasktracker.accounting.repository.tasks.TasksRepository;
import com.toughdevs.school.popugtasktracker.accounting.repository.tasks.model.TaskEntity;
import com.toughdevs.school.popugtasktracker.accounting.service.domain.TaskData;

@Service
public class ConsumerTasksStream {

	private final Logger logger = LoggerFactory.getLogger(ConsumerTasksStream.class);

	@Autowired
	private TasksRepository tasksRepository;

	@KafkaListener(topics = "tasks-stream", groupId = "accounting_group_id")
	public void listen(TaskData value, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
			@Header(KafkaHeaders.RECEIVED_KEY) String key) throws JsonMappingException, JsonProcessingException {
		logger.info(String.format("\n\n Consumed event from topic %s: key = %-10s value = %s \n\n", topic, key, value));
		
		TaskData task = value; 
		if (task != null) {
			TaskEntity taskEntity = tasksRepository.findByPublicId(task.getPublicId());
			if (taskEntity == null) {
				taskEntity = new TaskEntity();
				taskEntity.setPublicId(task.getPublicId());
				taskEntity.setCostAssign(getCostOfAssign());
				taskEntity.setCostDone(getCostOfDone());
			}
			taskEntity.setDescription(task.getDescription());
			tasksRepository.saveAndFlush(taskEntity);
		}
	}
	
	private BigDecimal getCostOfAssign() {
		return BigDecimal.valueOf(10).add(BigDecimal.valueOf(10).multiply(BigDecimal.valueOf(Math.random())));
	}
	
	private BigDecimal getCostOfDone() {
		return BigDecimal.valueOf(20).add(BigDecimal.valueOf(20).multiply(BigDecimal.valueOf(Math.random())));
	}
}
