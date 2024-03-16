package com.toughdevs.school.popugtasktracker.tasks.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toughdevs.school.popugtasktracker.tasks.account.domain.Account;
import com.toughdevs.school.popugtasktracker.tasks.kafka.ProducerTasksAssigned;
import com.toughdevs.school.popugtasktracker.tasks.kafka.ProducerTasksCompleted;
import com.toughdevs.school.popugtasktracker.tasks.kafka.ProducerTasksStream;
import com.toughdevs.school.popugtasktracker.tasks.repository.AccountsRepository;
import com.toughdevs.school.popugtasktracker.tasks.repository.TasksRepository;
import com.toughdevs.school.popugtasktracker.tasks.repository.model.AccountEntity;
import com.toughdevs.school.popugtasktracker.tasks.repository.model.TaskEntity;
import com.toughdevs.school.popugtasktracker.tasks.web.TaskCreateRequest;
import com.toughdevs.school.popugtasktracker.tasks.web.TaskData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TasksServiceImpl implements TasksService {

	@Autowired
	private TasksRepository tasksRepository;
	@Autowired
	private AccountsRepository accountsRepository;
	@Autowired
	private ProducerTasksAssigned producerTasksAssigned;
	@Autowired
	private ProducerTasksCompleted ProducerTasksCompleted;
	@Autowired
	private ProducerTasksStream producerTasksStream;
	

	@Override
	public List<TaskData> getTasks() {
		log.info("get list of tasks");
		List<TaskEntity> tasks = tasksRepository.findAll();
		return tasks.stream().map(r -> {
			return functionTaskDataFromRepoToRest(r);
		}).toList();
	}

	private TaskData functionTaskDataFromRepoToRest(TaskEntity r) {
		TaskData task = new TaskData();
		task.setId(r.getId());
		task.setDescription(r.getDescription());
		task.setStatus(r.getStatus());
		task.setPublicId(r.getPublicId());

		Account acc = new Account();
		acc.setId(r.getAssignedTo());
		task.setAssignedTo(acc);
		return task;
	}

	@Override
	public TaskData createNewTask(TaskCreateRequest req) {
		log.info("createNewTask with data:{}", req);
		TaskEntity task = new TaskEntity();
		task.setPublicId(UUID.randomUUID().toString());
		task.setDescription(req.getDescription());
		task.setStatus("PENDING");
		tasksRepository.saveAndFlush(task);
		// TODO: send CUD event for task created

		Optional<AccountEntity> accountRandom = accountsRepository.findByRole("EMPLOYEE").stream().findAny();
		task.setAssignedTo(accountRandom.get().getId());
		task.setStatus("INPROGRESS");
		tasksRepository.saveAndFlush(task);
		log.info("task created and assigned to: {}", accountRandom.get().getId());
		TaskData taskData = functionTaskDataFromRepoToRest(task);
		
		producerTasksStream.sendMessage("TASKS.CREATED", taskData);
//		producerTasksStream.produceTaskDetails("TASKS.CREATED", taskData);
		return taskData;
	}

	@Override
	public TaskData completeTask(Long taskId) {
		log.info("completeTask with id:{}", taskId);
		TaskEntity task = tasksRepository.getReferenceById(taskId);
		task.setStatus("DONE");
		task = tasksRepository.saveAndFlush(task);
		TaskData taskData = functionTaskDataFromRepoToRest(task);
		
		ProducerTasksCompleted.sendMessage("TASKS.DONE", taskData.getPublicId());
		return taskData;
	}

	@Override
	public List<TaskData> assignAll() {
		log.info("assign all");
		List<TaskData> tasksProcessed = new ArrayList<>();
		List<AccountEntity> accountsList = accountsRepository.findByRole("EMPLOYEE");
		tasksRepository.findByStatus("INPROGRESS").stream().forEach(task -> {
			task.setAssignedTo(accountsList.stream().findAny().get().getId());
			tasksProcessed.add(functionTaskDataFromRepoToRest(tasksRepository.save(task)));
			TaskData taskData = functionTaskDataFromRepoToRest(task);
			
			producerTasksAssigned.sendMessage(taskData.getPublicId(), taskData.getAssignedTo().getPublicId());
		});
		return tasksProcessed;
	}
}
