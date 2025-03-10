package com.backend.shell.service;

import com.backend.shell.model.Task;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service

public class FileService {

    private final String FILE_NAME = "tasks.json";
    private final String FILE_PATH = "src/main/resources/";

    private final ObjectMapper objectMapper;

    public FileService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Task> retrieveTasks() throws IOException {
        File file = new File(FILE_PATH + FILE_NAME);
        if (file.exists()) {
            ClassPathResource resource = new ClassPathResource(FILE_PATH + FILE_NAME);
            InputStream inputStream = resource.getInputStream();
            return objectMapper.readValue(inputStream, new TypeReference<List<Task>>() {});
        }
        return new ArrayList<>();
    }

    public void addTask(Task task) throws IOException {
        List<Task> tasks;
        File file = new File(FILE_PATH + FILE_NAME);
        if(!file.exists()){

            file.getParentFile().mkdirs(); //create parent directory
            file.createNewFile(); //create file
            tasks = new ArrayList<>();
        }
        else {
            tasks = retrieveTasks();
        }
        tasks.add(task);
        FileWriter fileWriter = new FileWriter(FILE_PATH + FILE_NAME);
        objectMapper.writeValue(fileWriter, tasks);

    }


}
