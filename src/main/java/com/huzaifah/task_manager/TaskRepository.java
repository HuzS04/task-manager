package com.huzaifah.task_manager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//repository - spring interface that gives you database operations (save,del,find) for free

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser_Id(Long userId);
}
// Task type of object the repo manages
// Long - type of the ID field
