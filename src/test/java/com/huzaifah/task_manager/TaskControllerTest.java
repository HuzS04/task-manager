package com.huzaifah.task_manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskController taskController;

    @Test
    void shouldReturnAllTasks() {
        // Arrange
        List<TaskDTO> fakeTasks = List.of(
                new TaskDTO(1L, "Buy groceries", false, "Alice"),
                new TaskDTO(2L, "Finish Java course", false, "Bob")
        );
        when(taskService.getAllTasks()).thenReturn(fakeTasks);

        // Act
        List<TaskDTO> response = taskController.getAllTasks();

        // Assert
        assertEquals(2, response.size());
        assertEquals("Buy groceries", response.get(0).getTitle());
    }

    @Test
    void shouldReturn404WhenTaskNotFound() {
        // Arrange
        when(taskService.getTaskById(99L))
                .thenThrow(new ResourceNotFoundException("Task not found"));

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            taskController.getTaskById(99L);
        });
    }

    @Test
    void shouldDeleteTask() {
        // Arrange
        doNothing().when(taskService).deleteTask(1L);

        // Act
        ResponseEntity<Void> response = taskController.deleteTask(1L);

        // Assert
        assertEquals(204, response.getStatusCode().value());
        verify(taskService, times(1)).deleteTask(1L);
    }
}