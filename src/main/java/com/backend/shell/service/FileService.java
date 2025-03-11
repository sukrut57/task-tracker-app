package com.backend.shell.service;

import com.backend.shell.model.Task;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    private final String FILE_NAME = "tasks.json";

    @Value("${file.base.path}")
    private String FILE_PATH;

    private final ObjectMapper objectMapper;

    public FileService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public List<Task> retrieveTasks() throws IOException {
        File file = new File(FILE_PATH + FILE_NAME);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(Files.newBufferedReader(Paths.get(FILE_PATH + FILE_NAME)), new TypeReference<List<Task>>() {});
    }

    public void addTask(Task task) throws IOException {
        List<Task> tasks = retrieveTasks();
        tasks.add(task);

        new File(FILE_PATH).mkdirs(); // Ensure directory exists
        try (FileWriter fileWriter = new FileWriter(FILE_PATH + FILE_NAME)) {
            objectMapper.writeValue(fileWriter, tasks);
        }
    }

    public void updateTask(Task task) throws IOException {
        List<Task> tasks = retrieveTasks();
        tasks.removeIf(t -> t.getId() == task.getId());
        tasks.add(task);
        if(!Files.exists(Paths.get(FILE_PATH + FILE_NAME))) {
            throw new FileNotFoundException("File not found");
        }
        try (FileWriter fileWriter = new FileWriter(FILE_PATH + FILE_NAME)) {
            objectMapper.writeValue(fileWriter, tasks);
        }
    }

    public void deleteTask(long id) throws IOException {
        List<Task> tasks = retrieveTasks();
        tasks.removeIf(t -> t.getId() == id);
        if(!Files.exists(Paths.get(FILE_PATH + FILE_NAME))) {
            throw new FileNotFoundException("File not found");
        }
        try (FileWriter fileWriter = new FileWriter(FILE_PATH + FILE_NAME)) {
            objectMapper.writeValue(fileWriter, tasks);
        }
    }
}
