package com.huzaifah.task_manager;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    public List<String> getAllTasks() {
        return List.of("Buy groceries", "Finish Java course", "Clean the house");
    }

    public List<TaskDTO> getTaskObjects() {
        List<Task> tasks = List.of(
                new Task(1L, "Buy groceries", false, 1),
                new Task(2L, "Finish Java course", false, 3),
                new Task(3L, "Clean the house", true, 2)
        );
        return tasks.stream()
                .map(task -> new TaskDTO(task.getId(), task.getTitle(), task.isCompleted()))
                .collect(Collectors.toList());
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