package com.toughdevs.school.popugtasktracker.tasks.service;

import java.util.List;

import com.toughdevs.school.popugtasktracker.tasks.web.TaskCreateRequest;
import com.toughdevs.school.popugtasktracker.tasks.web.TaskData;

public interface TasksService {

	List<TaskData> getTasks();

	TaskData createNewTask(TaskCreateRequest req);

	TaskData completeTask(Long taskId);

	List<TaskData> assignAll();

}
