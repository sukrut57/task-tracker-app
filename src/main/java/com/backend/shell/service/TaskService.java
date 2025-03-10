package com.backend.shell.service;

import com.backend.shell.model.Task;
import com.backend.shell.model.TaskStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final List<Task> globalTask = new ArrayList<>();


    public List<String> getAllTasks(){
        return printTask(globalTask);
    }

    public List<String> getAllTasksNotDone(){
        List<Task> notDoneTasks = new ArrayList<>();
        if(globalTask.isEmpty()){
            return null;
        }
        globalTask.stream().filter(task ->
                task.getStatus().equals(TaskStatus.todo)).forEach(notDoneTasks::add);
        return printTask(notDoneTasks);
    }


    public String createTask(String description){
        LocalDateTime currentDate = LocalDateTime.now();
        Task task = new Task(description, TaskStatus.todo, currentDate, currentDate);
        //todo save it to a json file
        globalTask.add(task);
        return "Task added successfully: " + task.getId();
    }

    public String updateTask(long id, String description){
        Task retrieveTask = globalTask.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
        if(retrieveTask == null){
            return "Task not found";
        }
        retrieveTask.setDescription(description);
        retrieveTask.setUpdatedAt(LocalDateTime.now());
        return "Task updated successfully";
    }

    private List<String> printTask(List<Task> tasks){
        List<String> taskList = new ArrayList<>();
        tasks.forEach(task -> taskList.add(task.getId() + " " + task.getDescription() + " " + task.getStatus() + " " +  task.getCreatedAt() + " " + task.getUpdatedAt()));
        return taskList;
    }

    public String deleteTask(long id) {
        Task retrieveTask = globalTask.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
        if(retrieveTask == null){
            return "Task not found";
        }
        return "Task deleted successfully";
    }

    public String markTaskInProgress(long id) {
        Task retrieveTask = globalTask.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
        if(retrieveTask == null){
            return "Task not found";
        }
        retrieveTask.setStatus(TaskStatus.inProgress);
        retrieveTask.setUpdatedAt(LocalDateTime.now());
        return "Task marked in progress";
    }


    public String markTaskAsDone(long id) {
        Task retrieveTask = globalTask.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
        if(retrieveTask == null){
            return "Task not found";
        }
        retrieveTask.setStatus(TaskStatus.done);
        retrieveTask.setUpdatedAt(LocalDateTime.now());
        return "Task marked as done";
    }

    public List<String> getAllTasksInProgress() {
        List<Task> taskList = globalTask.stream().filter(task -> task.getStatus().equals(TaskStatus.inProgress)).toList();
        if(taskList.isEmpty()){
            return new ArrayList<>();
        }
        return printTask(taskList);
    }

    public List<String> getAllTasksDone() {
        List<Task> taskList = globalTask.stream().filter(task -> task.getStatus().equals(TaskStatus.done)).toList();
        if(taskList.isEmpty()){
            return new ArrayList<>();
        }
        return printTask(taskList);
    }
}
