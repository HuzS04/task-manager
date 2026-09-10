package com.huzaifah.task_manager;

public class CommentDTO {
    private Long id;
    private String content;
    private String taskTitle;

    public CommentDTO(Long id, String content, String taskTitle){
        this.id = id;
        this.content = content;
        this.taskTitle = taskTitle;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTaskTitle() {
        return taskTitle;
    }
}
