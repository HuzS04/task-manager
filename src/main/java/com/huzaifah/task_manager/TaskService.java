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
                .map(task -> new TaskDTO(task.getId(), task.getTitle(), task.isCompleted(), task.getUserId()))
                .collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(task -> new TaskDTO(task.getId(), task.getTitle(), task.isCompleted(), task.getUserId()))
                .orElse(null);
    }

    public TaskDTO createTask(Task task) {
        Task saved = taskRepository.save(task);
        return new TaskDTO(saved.getId(), saved.getTitle(), saved.isCompleted(), saved.getUserId());
    }

    public TaskDTO updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setCompleted(updatedTask.isCompleted());
            Task saved = taskRepository.save(task);
            return new TaskDTO(saved.getId(), saved.getTitle(), saved.isCompleted(), saved.getUserId());
        }).orElse(null);
    }

    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<TaskDTO> getTasksByUser(Long userId){
        return taskRepository.findByUserId(userId).stream()
                .map(task -> new TaskDTO(task.getId(), task.getTitle(), task.isCompleted(), task.getUserId()))
                .collect(Collectors.toList());
    }
}