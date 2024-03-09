package com.toughdevs.school.popugtasktracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toughdevs.school.popugtasktracker.account.domain.Account;
import com.toughdevs.school.popugtasktracker.repository.AccountsRepository;
import com.toughdevs.school.popugtasktracker.repository.TasksRepository;
import com.toughdevs.school.popugtasktracker.repository.model.AccountEntity;
import com.toughdevs.school.popugtasktracker.repository.model.TaskEntity;
import com.toughdevs.school.popugtasktracker.web.domain.TaskCreateRequest;
import com.toughdevs.school.popugtasktracker.web.domain.TaskData;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TasksServiceImpl implements TasksService {

	@Autowired
	private TasksRepository tasksRepository;
	@Autowired
	private AccountsRepository accountsRepository;

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
		// TODO: send BE event for task assigned

		return functionTaskDataFromRepoToRest(task);
	}

	@Override
	public TaskData completeTask(@PathParam("taskId") Long taskId) {
		log.info("completeTask with id:{}", taskId);
		TaskEntity task = tasksRepository.getReferenceById(taskId);
		task.setStatus("DONE");
		task = tasksRepository.saveAndFlush(task);
		// TODO: send BE event for task completed

		return functionTaskDataFromRepoToRest(task);
	}

	@Override
	public List<TaskData> assignAll() {
		log.info("assign all");
		List<TaskData> tasksProcessed = new ArrayList<>();
		List<AccountEntity> accountsList = accountsRepository.findByRole("EMPLOYEE");
		tasksRepository.findByStatus("INPROGRESS").stream().forEach(task -> {
			task.setAssignedTo(accountsList.stream().findAny().get().getId());
			tasksProcessed.add(functionTaskDataFromRepoToRest(tasksRepository.save(task)));
			// TODO: create BE event for task assigned
		});
		return tasksProcessed;
	}
}
