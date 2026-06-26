package com.huzaifah.task_manager;

public class Task {
    private Long id;
    private String title;
    private boolean completed;
    private int priority;

    public Task(Long id, String title, boolean completed, int priority){
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
}
