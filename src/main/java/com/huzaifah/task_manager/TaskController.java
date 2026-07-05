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
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public TaskDTO getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/tasks")
    public TaskDTO createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/tasks/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/tasks/{id}")
    public String deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);
        return deleted ? "Task deleted" : "Task not found";
    }

    @GetMapping("/users/{id}/tasks")
    public List<TaskDTO> getTasksByUser(@PathVariable Long id){
        return taskService.getTasksByUser(id);
    }
}