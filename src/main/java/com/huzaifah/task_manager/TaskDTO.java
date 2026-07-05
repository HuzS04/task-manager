package com.huzaifah.task_manager;

public class TaskDTO {
    private Long id;
    private String title;
    private boolean completed;
    private Long userId;

    public TaskDTO(Long id, String title, boolean completed, Long userId) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.userId = userId;
    }

    public Long getId() {
        return id; }

    public String getTitle() {
        return title; }

    public boolean isCompleted() {
        return completed; }

    public Long getUserId() {
        return userId;
    }
}
