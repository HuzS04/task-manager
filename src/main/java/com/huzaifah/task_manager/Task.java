package com.huzaifah.task_manager;

import jakarta.persistence.*;
//JPA (Java Persistence API) - standard way java talks to databases, use annotations to tell it how to map to db

@Entity //this class maps to a database table
@Table(name = "tasks") //the specific table it maps to
public class Task {

    @Id //primary key marking
    @GeneratedValue(strategy = GenerationType.IDENTITY) //let db generate this value itself
    private Long id;

    private String title;
    private boolean completed;
    private int priority;

    public Task() {} //JPA requires a no argument constructor

    public Task(Long id, String title, boolean completed, int priority) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.priority = priority;
    }

    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public boolean isCompleted(){
        return completed;
    }

    public int getPriority() {
        return priority;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
