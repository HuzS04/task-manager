package com.huzaifah.task_manager;

public class NoteDTO {
    private Long id;
    private String content;

    public NoteDTO(Long id, String content){
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}
