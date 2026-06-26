package com.huzaifah.task_manager;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    public List<String> getAllTasks() {
        return List.of("Buy groceries", "Finish Java course", "Clean the house");
    }

    public List<Task> getTaskObjects() {
        return List.of(
                new Task(1L, "Buy groceries", false),
                new Task(2L, "Finish Java course", false),
                new Task(3L, "Clean the house", true)
        );
    }

    public String getTaskById(Long id) {
        return "You asked for task with ID: " + id;
    }

    public String searchTasks(String keyword) {
        return "Searching tasks for: " + keyword;
    }

    public Task createTask(Task task) {
        System.out.println("Received task: " + task.getTitle());
        return task;
    }
}