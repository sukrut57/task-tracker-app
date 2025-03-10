package com.backend.shell.commands;

import com.backend.shell.model.Task;
import com.backend.shell.service.TaskService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class TaskComponent {

    private final TaskService taskService;

    public TaskComponent(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Add a task
     * @param description
     * Description of the task
     * @return
     * Task added successfully
     */
    @ShellMethod(key = "add", value = "Add a task")
    public String addTask(String description) {
        return taskService.createTask(description);
    }

    /**
     * Update a task
     * @param id
     * Id of the task
     * @param description
     * Description of the task
     * @return
     * Task updated successfully
     */
    @ShellMethod(key = "update", value = "Update a task")
    public String updateTask(long id, String description){
        return taskService.updateTask(id, description);
    }

    /**
     * Mark a task as done
     * @param id
     * Id of the task
     * @return
     * Task marked as done
     */
    @ShellMethod(key ="mark-in-progress", value = "Mark a task in progress")
    public String markTaskInProgress(long id){
        return taskService.markTaskInProgress(id);
    }

    /**
     * Mark a task as done
     * @param id
     * Id of the task
     * @return
     * Task marked as done
     */
    @ShellMethod(key = "done", value = "Mark a task as done")
    public String markTaskAsDone(long id){
        return taskService.markTaskAsDone(id);
    }

    /**
     * Delete a task
     * @param id
     * Id of the task
     * @return
     * Task deleted successfully
     */
    @ShellMethod(key = "delete", value = "Delete a task")
    public String deleteTask(long id){
        return taskService.deleteTask(id);
    }

    /**
     * List all tasks
     * @return
     * List of all tasks
     */
    @ShellMethod(key="list", value = "list all tasks")
    public List<String> listTasks(){
        return taskService.getAllTasks();
    }

    /**
     * List all tasks not done
     * @return
     * List of all tasks not done
     */

    @ShellMethod(key = "list todo", value = "List all tasks not done")
    public List<String> listTasksNotDone() {
        return taskService.getAllTasksNotDone();
    }

    @ShellMethod(key = "list in-progress", value = "List all tasks in progress")
    public List<String> listTasksInProgress() {
        return taskService.getAllTasksInProgress();
    }

    @ShellMethod(key = "list done", value = "List all tasks done")
    public List<String> listTasksDone() {
        return taskService.getAllTasksDone();
    }




}
