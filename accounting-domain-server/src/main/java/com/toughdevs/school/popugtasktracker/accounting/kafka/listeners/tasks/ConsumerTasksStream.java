package com.toughdevs.school.popugtasktracker.accounting.kafka.listeners.tasks;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.toughdevs.school.popugtasktracker.accounting.repository.tasks.TasksRepository;
import com.toughdevs.school.popugtasktracker.accounting.repository.tasks.model.TaskEntity;
import com.toughdevs.school.popugtasktracker.tasks.schema.Task_v1;
import com.toughdevs.school.popugtasktracker.tasks.schema.Task_v2;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerTasksStream {

	@Autowired
	private TasksRepository tasksRepository;

	@KafkaListener(topics = "tasks-stream", groupId = "accounting_group_tasks", properties = {
			"spring.json.value.default.type=com.toughdevs.school.popugtasktracker.tasks.schema.Task_v1" })
	public void listen(Task_v1 task, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
			@Header(KafkaHeaders.RECEIVED_KEY) String key) throws JsonMappingException, JsonProcessingException {
		log.info(String.format("\n\n Consumed event from topic %s: key = %-10s value = %s \n\n", topic, key, task));

		if (task != null) {
			TaskEntity taskEntity = tasksRepository.findByPublicId(String.valueOf(task.getPublicId()));
			if (taskEntity == null) {
				taskEntity = new TaskEntity();
				taskEntity.setPublicId(String.valueOf(task.getPublicId()));
				taskEntity.setCostAssign(getCostOfAssign());
				taskEntity.setCostDone(getCostOfDone());
			}
			taskEntity.setDescription(String.valueOf(task.getDescription()));
			tasksRepository.saveAndFlush(taskEntity);
		}
	}

	@KafkaListener(topics = "tasks-stream", groupId = "accounting_group_tasks", properties = {
			"spring.json.value.default.type=com.toughdevs.school.popugtasktracker.tasks.schema.Task_v2" })
	public void listen(Task_v2 task, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
			@Header(KafkaHeaders.RECEIVED_KEY) String key) throws JsonMappingException, JsonProcessingException {
		log.info(String.format("\n\n Consumed event from topic %s: key = %-10s value = %s \n\n", topic, key, task));

		if (task != null) {
			TaskEntity taskEntity = tasksRepository.findByPublicId(String.valueOf(task.getPublicId()));
			if (taskEntity == null) {
				taskEntity = new TaskEntity();
				taskEntity.setPublicId(String.valueOf(task.getPublicId()));
				taskEntity.setCostAssign(getCostOfAssign());
				taskEntity.setCostDone(getCostOfDone());
			}
			taskEntity.setDescription(String.valueOf(task.getDescription()));
			tasksRepository.saveAndFlush(taskEntity);
		}
	}

//	@StreamListener(Processor.INPUT)
//	public void consumeTaskStreamDetails(Task task) {
//	    log.info("Let's process task stream details: {}", task);
//		if (task != null) {
//			TaskEntity taskEntity = tasksRepository.findByPublicId(String.valueOf(task.getPublicId()));
//			if (taskEntity == null) {
//				taskEntity = new TaskEntity();
//				taskEntity.setPublicId(String.valueOf(task.getPublicId()));
//				taskEntity.setCostAssign(getCostOfAssign());
//				taskEntity.setCostDone(getCostOfDone());
//			}
//			taskEntity.setDescription(String.valueOf(task.getDescription()));
//			tasksRepository.saveAndFlush(taskEntity);
//		}
//	}

	private BigDecimal getCostOfAssign() {
		return BigDecimal.valueOf(10).add(BigDecimal.valueOf(10).multiply(BigDecimal.valueOf(Math.random())));
	}

	private BigDecimal getCostOfDone() {
		return BigDecimal.valueOf(20).add(BigDecimal.valueOf(20).multiply(BigDecimal.valueOf(Math.random())));
	}
}
