package com.huzaifah.task_manager;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;

    public CommentService(TaskRepository taskRepository, CommentRepository commentRepository){
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
    }

    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll().stream()
                .map(comment -> new CommentDTO(comment.getId(), comment.getContent(), comment.getTask() != null ? comment.getTask().getTitle() : null))
                .collect(Collectors.toList());
    }

    public List<CommentDTO> getCommentsByTask(Long taskId) {
        return commentRepository.findByTask_Id(taskId).stream()
                .map(comment -> new CommentDTO(comment.getId(), comment.getContent(),
                        comment.getTask() != null ? comment.getTask().getTitle() : null))
                .collect(Collectors.toList());
    }

    public CommentDTO createComment(Comment comment) {
        if (comment.getTask() != null && comment.getTask().getId() != null) {
            Task fullTask = taskRepository.findById(comment.getTask().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
            comment.setTask(fullTask);
        }
        Comment saved = commentRepository.save(comment);
        return new CommentDTO(
                saved.getId(),
                saved.getContent(),
                saved.getTask() != null ? saved.getTask().getTitle() : null
        );
    }

    public void deleteComment(Long id){
        if (!commentRepository.existsById(id)){
            throw new ResourceNotFoundException("Comment not found with id: " + id);
        }
        commentRepository.deleteById(id);
    }
}
