package com.huzaifah.task_manager;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String getTasks() {
        return "Here are your tasks";
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the Task Manager";
    }

    @GetMapping("/tasks/all")
    public List<String> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/objects")
    public List<TaskDTO> getTaskObjects() {
        return taskService.getTaskObjects();
    }

    @GetMapping("/tasks/{id}")
    public String getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/tasks/search")
    public String searchTasks(@RequestParam String keyword) {
        return taskService.searchTasks(keyword);
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }
}