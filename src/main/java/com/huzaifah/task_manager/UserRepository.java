package com.huzaifah.task_manager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//repository - spring interface that gives you database operations (save,del,find) for free

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
// User - type of object the repo manages
// Long - type of the ID field
