package com.huzaifah.task_manager;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();

    public TaskService() {
        tasks.add(new Task(1L, "Buy groceries", false, 1));
        tasks.add(new Task(2L, "Finish Java course", false, 3));
        tasks.add(new Task(3L, "Clean the house", true, 2));
    }

    public List<TaskDTO> getAllTasks() {
        return tasks.stream()
                .map(task -> new TaskDTO(task.getId(), task.getTitle(), task.isCompleted()))
                .collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return new TaskDTO(task.getId(), task.getTitle(), task.isCompleted());
            }
        }
        return null;
    }

    public String searchTasks(String keyword) {
        return "Searching tasks for: " + keyword;
    }

    public TaskDTO createTask(Task task) {
        tasks.add(task);
        return new TaskDTO(task.getId(), task.getTitle(), task.isCompleted());
    }

    public TaskDTO updateTask(Long id, Task updatedTask) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setCompleted(updatedTask.isCompleted());
                return new TaskDTO(task.getId(), task.getTitle(), task.isCompleted());
            }
        }
        return null;
    }

    public boolean deleteTask(Long id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }
}