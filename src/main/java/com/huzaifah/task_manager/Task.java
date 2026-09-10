package com.huzaifah.task_manager;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    private Boolean completed;

    @Min(value = 1, message = "Priority must be at least 1")
    @Max(value = 3, message = "Priority must be at most 3")
    private Integer priority;

    // @ManyToOne — many tasks can belong to one user
    // FetchType.LAZY — don't load the User from the database until we actually call getUser()
    // more efficient than EAGER which would load the User on every single Task query
    @ManyToOne(fetch = FetchType.LAZY)
    // tells JPA which column in the tasks table holds the foreign key link to users
    @JoinColumn(name = "user_id")
    private User user;

    public Task() {} // JPA requires a no-arg constructor

    public Task(String title, Boolean completed, Integer priority) {
        this.title = title;
        this.completed = completed;
        this.priority = priority;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Boolean isCompleted() { return completed; }
    public Integer getPriority() { return priority; }
    public User getUser() { return user; }

    public void setCompleted(Boolean completed) { this.completed = completed; }
    public void setPriority(Integer priority) { this.priority = priority; }
    public void setUser(User user) { this.user = user; }

    public void setTitle(String title) { this.title = title; }
}