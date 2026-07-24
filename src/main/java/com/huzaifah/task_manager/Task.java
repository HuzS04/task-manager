package com.huzaifah.task_manager;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private boolean completed;
    private int priority;

    // @ManyToOne — many tasks can belong to one user
    // FetchType.LAZY — don't load the User from the database until we actually call getUser()
    // more efficient than EAGER which would load the User on every single Task query
    @ManyToOne(fetch = FetchType.LAZY)
    // tells JPA which column in the tasks table holds the foreign key link to users
    @JoinColumn(name = "user_id")
    private User user;

    public Task() {} // JPA requires a no-arg constructor

    public Task(String title, boolean completed, int priority) {
        this.title = title;
        this.completed = completed;
        this.priority = priority;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }
    public int getPriority() { return priority; }
    public User getUser() { return user; }

    public void setCompleted(boolean completed) { this.completed = completed; }
    public void setUser(User user) { this.user = user; }
}