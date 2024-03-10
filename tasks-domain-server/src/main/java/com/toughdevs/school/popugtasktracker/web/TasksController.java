package com.toughdevs.school.popugtasktracker.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.toughdevs.school.popugtasktracker.service.TasksServiceImpl;
import com.toughdevs.school.popugtasktracker.web.domain.TaskCreateRequest;
import com.toughdevs.school.popugtasktracker.web.domain.TaskData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TasksController {
	
	@Autowired
	private TasksServiceImpl tasksService;

    @GetMapping("/tasks")
    public List<TaskData> getTasks() {
    	log.info("get list of tasks");
        return tasksService.getTasks();
    }
    
    @PostMapping("/tasks/create")
    public TaskData createNewTask(@RequestBody TaskCreateRequest req) {
    	log.info("createNewTask with data:{}", req);
        return tasksService.createNewTask(req);
    }
    
    @PostMapping("/tasks/{taskId}/complete")
    public TaskData completeTask(@PathVariable("taskId") Long taskId) {
    	log.info("completeTask with id:{}", taskId);
        return tasksService.completeTask(taskId);
    }
    
    @PostMapping("/tasks/assignAll")
    public List<TaskData> assignAll() {
    	log.info("assign all");
        return tasksService.assignAll();
    }
}
