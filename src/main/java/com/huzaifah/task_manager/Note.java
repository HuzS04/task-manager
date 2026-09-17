package com.huzaifah.task_manager;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Content cannot be empty")
    private String content;

    private Boolean pinned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Note() {} // JPA requires no-arg constructor

    public Note(Long id, String content, Boolean pinned) {
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

    public Boolean isPinned() {
        return pinned;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
