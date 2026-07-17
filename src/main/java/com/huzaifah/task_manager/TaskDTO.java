package com.huzaifah.task_manager;

public class TaskDTO {
    private Long id;
    private String title;
    private boolean completed;
    private String userName;

    public TaskDTO(Long id, String title, boolean completed, String userName) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.userName = userName;
    }

    public Long getId() {
        return id; }

    public String getTitle() {
        return title; }

    public boolean isCompleted() {
        return completed; }

    public String getUserName() {
        return userName;
    }
}
