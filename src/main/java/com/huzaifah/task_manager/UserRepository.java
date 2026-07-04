package com.huzaifah.task_manager;

import org.springframework.data.jpa.repository.JpaRepository;
//repository - spring interface that gives you database operations (save,del,find) for free

public interface UserRepository extends JpaRepository<User, Long> {
}
// User - type of object the repo manages
// Long - type of the ID field
