package com.toughdevs.school.popugtasktracker.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TasksController {

    @GetMapping("/tasks")
    public String[] getTasks() {
        return new String[] { "Task 1", "Task 2", "Task 3" };
    }
}
