package com.backend.shell.service;

import com.backend.shell.model.Task;
import com.backend.shell.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final FileService fileService;

    //private final List<Task> globalTask = new ArrayList<>();

    public TaskService(FileService fileService) {
        this.fileService = fileService;
    }

    public List<String> getAllTasks() throws IOException {
        return printTask(fileService.retrieveTasks());
    }

    public List<String> getAllTasksNotDone() throws IOException {
        List<Task> taskList = fileService.retrieveTasks();
        List<Task>  notDoneTasks = new ArrayList<>();
        if(taskList.isEmpty()){
            return new ArrayList<>();
        }
        taskList.stream().filter(task ->
                task.getStatus().equals(TaskStatus.todo)).forEach(notDoneTasks::add);
        return printTask(notDoneTasks);
    }


    public String createTask(String description) throws IOException {
        LocalDateTime currentDate = LocalDateTime.now();
        Task task = new Task(description, TaskStatus.todo, currentDate, currentDate);
        //todo save it to a json file
        fileService.addTask(task);
        return "Task added successfully: " + task.getId();
    }

    public String updateTask(long id, String description) throws IOException {
        Task retrieveTask = fileService.retrieveTasks().stream().filter(task -> task.getId() == id).findFirst().orElse(null);
        if(retrieveTask == null){
            return "Task not found";
        }
        retrieveTask.setDescription(description);
        retrieveTask.setUpdatedAt(LocalDateTime.now());
        fileService.updateTask(retrieveTask);
        return "Task updated successfully";
    }

    private List<String> printTask(List<Task> tasks){
        List<String> taskList = new ArrayList<>();
        tasks.forEach(task -> taskList.add(task.getId() + " " + task.getDescription() + " " + task.getStatus() + " " +  task.getCreatedAt() + " " + task.getUpdatedAt()));
        return taskList;
    }

    public String deleteTask(long id) throws IOException {
        Task retrieveTask = fileService.retrieveTasks().stream().filter(task -> task.getId() == id).findFirst().orElse(null);
        if(retrieveTask == null){
            return "Task not found";
        }
        fileService.deleteTask(id);
        return "Task deleted successfully";
    }

    public String markTaskInProgress(long id) throws IOException {
        Task retrieveTask = fileService.retrieveTasks().stream().filter(task -> task.getId() == id).findFirst().orElse(null);
        if(retrieveTask == null){
            return "Task not found";
        }
        retrieveTask.setStatus(TaskStatus.inProgress);
        retrieveTask.setUpdatedAt(LocalDateTime.now());
        fileService.updateTask(retrieveTask);
        return "Task marked in progress";
    }


    public String markTaskAsDone(long id) throws IOException {
        Task retrieveTask = fileService.retrieveTasks().stream().filter(task -> task.getId() == id).findFirst().orElse(null);
        if(retrieveTask == null){
            return "Task not found";
        }
        retrieveTask.setStatus(TaskStatus.done);
        retrieveTask.setUpdatedAt(LocalDateTime.now());
        fileService.updateTask(retrieveTask);
        return "Task marked as done";
    }

    public List<String> getAllTasksInProgress() throws IOException {
        List<Task> taskList = fileService.retrieveTasks().stream().filter(task -> task.getStatus().equals(TaskStatus.inProgress)).toList();
        if(taskList.isEmpty()){
            return new ArrayList<>();
        }
        return printTask(taskList);
    }

    public List<String> getAllTasksDone() throws IOException {
        List<Task> taskList = fileService.retrieveTasks().stream().filter(task -> task.getStatus().equals(TaskStatus.done)).toList();
        if(taskList.isEmpty()){
            return new ArrayList<>();
        }
        return printTask(taskList);
    }
}
