package com.huzaifah.task_manager;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public List<CommentDTO> getAllComments(){
        return commentService.getAllComments();
    }

    @GetMapping("/tasks/{id}/comments")
    public List<CommentDTO> getCommentById(@PathVariable Long id){
        return commentService.getCommentsByTask(id);
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody Comment comment) {
        CommentDTO created = commentService.createComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
