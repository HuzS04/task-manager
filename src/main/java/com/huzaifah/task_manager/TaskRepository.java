package com.huzaifah.task_manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
//repository - spring interface that gives you database operations (save,del,find) for free

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser_Id(Long userId);

    @Query("SELECT t FROM Task t WHERE t.completed = false")
    List<Task> findIncompleteTasks();

    @Query("SELECT t FROM Task t WHERE t.title LIKE %:keyword%")
    List<Task> searchByTitle(@Param("keyword") String keyword);

    @Query("SELECT t FROM Task t WHERE t.user.id = :userId ORDER BY t.priority DESC")
    List<Task> findTasksByUserOrderedByPriority(@Param("userId") Long userId);
}
// Task type of object the repo manages
// Long - type of the ID field
