package com.backend.shell.commands;

import com.backend.shell.service.TaskService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.util.ArrayList;
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
        try{
            return taskService.createTask(description);

        }catch (Exception e){
           return "Unable to add a task";
        }
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
        try{
            return taskService.updateTask(id, description);
        }
        catch (Exception e){
            return "Unable to add a task";
        }
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
        try{
            return taskService.markTaskInProgress(id);
        }
        catch (Exception e){
            return "Unable to add a task";
        }
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
        try{
            return taskService.markTaskAsDone(id);
        }
        catch (Exception e){
            return "Unable to add a task";
        }
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
        try{
            return taskService.deleteTask(id);
        }
        catch (Exception e){
            return "Unable to add a task";
        }    }

    /**
     * List all tasks
     * @return
     * List of all tasks
     */
    @ShellMethod(key="list", value = "list all tasks")
    public List<String> listTasks(){
        try{
            return taskService.getAllTasks();
        }
        catch (Exception e){
            return new ArrayList<>(List.of("Unable to list tasks"));
        }
    }

    /**
     * List all tasks not done
     * @return
     * List of all tasks not done
     */

    @ShellMethod(key = "list todo", value = "List all tasks not done")
    public List<String> listTasksNotDone() {
        try{
            return taskService.getAllTasksNotDone();
        }
        catch (Exception e){
            return new ArrayList<>(List.of("Unable to list tasks"));
        }
    }

    @ShellMethod(key = "list in-progress", value = "List all tasks in progress")
    public List<String> listTasksInProgress() {
        try{
            return taskService.getAllTasksInProgress();
        }
        catch (Exception e){
            return new ArrayList<>(List.of("Unable to list tasks"));
        }
    }

    @ShellMethod(key = "list done", value = "List all tasks done")
    public List<String> listTasksDone() {
        try{
            return taskService.getAllTasksDone();
        }
        catch (Exception e){
            return new ArrayList<>(List.of("Unable to list tasks"));
        }
    }




}
