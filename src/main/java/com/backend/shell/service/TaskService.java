package com.backend.shell.service;

import com.backend.shell.model.Task;
import com.backend.shell.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService {

    public String createTask(String description){
        LocalDateTime currentDate = LocalDateTime.now();
        Task task = new Task(description, TaskStatus.todo, currentDate, currentDate);
        //save it to a json file
        return "Task added successfully" + task.getId();
    }

}
