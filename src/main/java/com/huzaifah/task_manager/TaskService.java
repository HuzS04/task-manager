package com.huzaifah.task_manager;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> new TaskDTO(task.getId(), task.getTitle(), task.isCompleted()))
                .collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(task -> new TaskDTO(task.getId(), task.getTitle(), task.isCompleted()))
                .orElse(null);
    }

    public TaskDTO createTask(Task task) {
        Task saved = taskRepository.save(task);
        return new TaskDTO(saved.getId(), saved.getTitle(), saved.isCompleted());
    }

    public TaskDTO updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setCompleted(updatedTask.isCompleted());
            Task saved = taskRepository.save(task);
            return new TaskDTO(saved.getId(), saved.getTitle(), saved.isCompleted());
        }).orElse(null);
    }

    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public String searchTasks(String keyword) {
        return "Searching tasks for: " + keyword;
    }

    public List<String> getAllTaskTitles() {
        return taskRepository.findAll().stream()
                .map(Task::getTitle)
                .collect(Collectors.toList());
    }
}