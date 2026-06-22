package com.huzaifah.task_manager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    @GetMapping("/tasks")
    public String getTasks(){
        return "Here are your tasks";
    }

    @GetMapping("/")
    public String home(){
        return "Welcome to the Task Manager";
    }

    @GetMapping("/tasks/all")
    public List<String> getAllTasks(){
        return List.of("Buy groceries", "Finish Java course", "Clean the house");
    }

    @GetMapping("/tasks/objects")
    public List<Task> getTaskObjects(){
        return List.of(
                new Task(1L, "Buy groceries", false),
                new Task(2L, "Finish Java course", false),
                new Task(3L, "Clean the house", true)
        );
    }
}
