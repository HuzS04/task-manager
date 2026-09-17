package com.huzaifah.task_manager;

public class NoteDTO {
    private Long id;
    private String content;
    private String userName;

    public NoteDTO(Long id, String content, String userName) {
        this.id = id;
        this.content = content;
        this.userName = userName;
    }

    public Long getId() { return id; }
    public String getContent() { return content; }
    public String getUserName() { return userName; }
}
