package com.huzaifah.task_manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //tells Junit to use mockito for this test class
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository; // create a fake taskRepo, all the same methods but does nothing by default (returns null)

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskService taskService; //creates real task service and injects the mocks into it
     // task service is real but its taskRepo dependency is fake

    @Test
    void shouldReturnAllTasks() {
        // Arrange — tell the mock what to return when findAll() is called
        Task task1 = new Task("Buy groceries", false, 1);
        Task task2 = new Task("Walk the dog", false, 2);
        when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

        // Act
        List<TaskDTO> result = taskService.getAllTasks();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Buy groceries", result.get(0).getTitle());
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {
        // Arrange — tell the mock to return empty when findById is called
        when(taskRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            taskService.getTaskById(999L);
        });
    }

    @Test
    void shouldDeleteTask() {
        // Arrange
        when(taskRepository.existsById(1L)).thenReturn(true);

        // Act
        taskService.deleteTask(1L);

        // Assert — verify deleteById was actually called
        verify(taskRepository).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentTask(){

        when(taskRepository.existsById(999L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            taskService.deleteTask(999L);
        });
    }
}