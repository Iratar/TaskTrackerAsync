package com.toughdevs.school.popugtasktracker.tasks.kafka;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.toughdevs.school.popugtasktracker.tasks.web.TaskData;

@Service
public class ProducerTasksStream {

	private static final Logger logger = LoggerFactory.getLogger(ProducerTasksStream.class);
	private static final String TOPIC = "tasks-stream";

	@Autowired
	private KafkaTemplate<String, TaskData> kafkaTemplate;

	public void sendMessage(String key, TaskData value) {
		
		TaskData task = new TaskData();
		task.setPublicId(value.getPublicId());
		task.setDescription(value.getDescription());
//		task.setStatus(value.getStatus());
//		task.setAssignedTo(value.getAssignedTo().getId());
		CompletableFuture<SendResult<String, TaskData>> future = kafkaTemplate.send(TOPIC, key, task);
		future.whenComplete((result, ex) -> {
			if (ex == null) {
				logger.info("TOPIC: {}, Sent message=[{}] with offset=[{}]", TOPIC, key, result.getRecordMetadata().offset());
			} else {
				logger.info("TOPIC: {}, Unable to send message=[{}] due to : {}", TOPIC, key, ex.getMessage());
			}
		});
	}

//	@Autowired
//	private Processor processor;
//	
//
//	public void produceTaskDetails(String key, TaskData value) {
//
//		Task task = new Task();
//		task.setPublicId(value.getPublicId());
//		task.setDescription(value.getDescription());
////		task.setStatus(value.getStatus());
////		task.setAssignedTo(value.getAssignedTo().getId());
//
//		Message<Task> message = MessageBuilder.withPayload(task).build();
//
//		processor.output().send(message);
//		logger.info("TOPIC: {}, Unable to send message=[{}] due to : {}", TOPIC, key, value);
//	}
}
