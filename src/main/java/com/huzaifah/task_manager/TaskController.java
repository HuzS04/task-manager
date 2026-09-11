package com.huzaifah.task_manager;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/test-token")
    public String testToken() {
        return jwtUtil.generateToken("bob@email.com");
    }

    @GetMapping("/tasks")
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public TaskDTO getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // In TaskController — createTask updated

    @PostMapping("/tasks")
// ResponseEntity<TaskDTO> gives you control over BOTH status code AND body
// before this, Spring defaulted to 200 OK for everything
// 201 Created is the correct professional status for POST endpoints that create resources
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody Task task) {
        TaskDTO created = taskService.createTask(task);
        // status(HttpStatus.CREATED) = 201
        // body(created) = the TaskDTO as JSON
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        TaskDTO updated = taskService.updateTask(id, task);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{id}/tasks")
    public List<TaskDTO> getTasksByUser(@PathVariable Long id){
        return taskService.getTasksByUser(id);
    }

    @GetMapping("/tasks/incomplete")
    public List<TaskDTO> getIncompleteTasks() {
        return taskService.getIncompleteTasks();
    }

    @GetMapping("/tasks/search")
    public List<TaskDTO> searchTasks(@RequestParam String keyword) {
        return taskService.searchByTitle(keyword);
    }

    @GetMapping("/users/{id}/tasks/priority")
    public List<TaskDTO> getTasksByUserOrderedByPriority(@PathVariable Long id) {
        return taskService.findTasksByUserOrderedByPriority(id);
    }
}