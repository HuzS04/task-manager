package com.huzaifah.task_manager;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Comment cannot be empty")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    public Comment(){}

    public Comment(String content){
        this.content = content;
    }

    public Long getId(){
        return id;
    }

    public String getContent(){
        return content;
    }

    public Task getTask(){
        return task;
    }

    public void setTask(Task fullTask) {
        this.task = fullTask;
    }
}
