package com.toughdevs.school.popugtasktracker.service;

import java.util.List;

import com.toughdevs.school.popugtasktracker.web.domain.TaskCreateRequest;
import com.toughdevs.school.popugtasktracker.web.domain.TaskData;

public interface TasksService {

	List<TaskData> getTasks();

	TaskData createNewTask(TaskCreateRequest req);

	TaskData completeTask(Long taskId);

	List<TaskData> assignAll();

}
