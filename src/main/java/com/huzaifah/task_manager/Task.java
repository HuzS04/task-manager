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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task() {}

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