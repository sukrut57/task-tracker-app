package com.backend.shell.model;

import java.time.LocalDateTime;

public class Task {
    private static long counter = 0;
    private long id;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Task() {
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Task(String description, TaskStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = counter++;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public Task(String description, TaskStatus status, LocalDateTime updatedAt) {
        this.id = counter++;
        this.description = description;
        this.status = status;
        this.updatedAt = updatedAt;
    }
}
