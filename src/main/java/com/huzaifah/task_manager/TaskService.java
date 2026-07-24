package com.huzaifah.task_manager;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> new TaskDTO(task.getId(), task.getTitle(), task.isCompleted(), task.getUser() != null ? task.getUser().getName() : null))
                .collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(task -> new TaskDTO(task.getId(), task.getTitle(), task.isCompleted(), task.getUser() != null ? task.getUser().getName() : null))
                .orElse(null);
    }

    public TaskDTO createTask(Task task) {
        if (task.getUser() != null && task.getUser().getId() != null) {
            User fullUser = userRepository.findById(task.getUser().getId()).orElse(null);
            task.setUser(fullUser);
        }
        Task saved = taskRepository.save(task);
        return new TaskDTO(saved.getId(), saved.getTitle(), saved.isCompleted(),
                saved.getUser() != null ? saved.getUser().getName() : null);
    }

    public TaskDTO updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setCompleted(updatedTask.isCompleted());
            Task saved = taskRepository.save(task);
            return new TaskDTO(saved.getId(), saved.getTitle(), saved.isCompleted(), saved.getUser() != null ? task.getUser().getName() : null);
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
        return taskRepository.findByUser_Id(userId).stream()
                .map(task -> new TaskDTO(task.getId(), task.getTitle(), task.isCompleted(), task.getUser() != null ? task.getUser().getName() : null))
                .collect(Collectors.toList());
    }
}