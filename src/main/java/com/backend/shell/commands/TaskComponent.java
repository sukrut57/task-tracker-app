package com.backend.shell.commands;

import com.backend.shell.service.TaskService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class TaskComponent {

    private final TaskService taskService;

    public TaskComponent(TaskService taskService) {
        this.taskService = taskService;
    }

    @ShellMethod(key = "addTask", value = "Add a task")
    public String addTask(String description){
        return taskService.createTask(description);
    }
}
