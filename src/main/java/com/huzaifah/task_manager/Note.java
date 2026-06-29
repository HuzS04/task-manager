package com.huzaifah.task_manager;

public class Note {
    private Long id;
    private String content;
    private boolean pinned;

    public Note(Long id, String content, boolean pinned){
        this.id = id;
        this.content = content;
        this.pinned = pinned;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean isPinned() {
        return pinned;
    }

}
