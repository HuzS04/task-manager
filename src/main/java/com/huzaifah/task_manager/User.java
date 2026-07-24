package com.huzaifah.task_manager;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // @OneToMany — one user has many tasks
    // mappedBy = "user" — the relationship is already defined on the Task side
    // in the field called "user" — don't create another foreign key column here
    // cascade = CascadeType.ALL — whatever happens to the User, happens to their Tasks too
    // e.g. delete a User → all their Tasks get deleted automatically
    // orphanRemoval = true — if a Task is removed from this list, delete it from the database too
    // prevents orphaned tasks with no user pointing to a deleted user
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public User() {}

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Task> getTasks() { return tasks; }
}