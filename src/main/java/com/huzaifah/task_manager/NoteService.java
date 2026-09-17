package com.huzaifah.task_manager;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository){
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public List<NoteDTO> getAllNotes(){
        return noteRepository.findAll().stream()
                .map(note -> new NoteDTO(note.getId(), note.getContent(), note.getUser() != null ? note.getUser().getName() : null))
                .collect(Collectors.toList());
    }

    public NoteDTO getNoteById(Long id){
        return noteRepository.findById(id)
                .map(note -> new NoteDTO(
                        note.getId(),
                        note.getContent(),
                        note.getUser() != null ? note.getUser().getName() : null
                ))
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    /*
    public NoteDTO createNote(Note note) {
        if (note.getUser() != null && note.getUser().getId() != null) {
            User fullUser = userRepository.findById(note.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            note.setUser(fullUser);
        }
        Note saved = noteRepository.save(note);
        return new NoteDTO(saved.getId(), saved.getContent(),
                saved.getUser() != null ? saved.getUser().getName() : null);

    }
    */

    public NoteDTO createNote(Note note) {
        System.out.println("Note user: " + note.getUser());
        System.out.println("Note user id: " + (note.getUser() != null ? note.getUser().getId() : "null"));

        if (note.getUser() != null && note.getUser().getId() != null) {
            User fullUser = userRepository.findById(note.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            note.setUser(fullUser);
        }
        Note saved = noteRepository.save(note);
        return new NoteDTO(saved.getId(), saved.getContent(),
                saved.getUser() != null ? saved.getUser().getName() : null);
    }

    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Note not found with id: " + id);
        }
        noteRepository.deleteById(id);
    }

    public List<NoteDTO> getNotesByUser(Long userId) {
        return noteRepository.findByUser_Id(userId).stream()
                .map(note -> new NoteDTO(note.getId(), note.getContent(),
                        note.getUser() != null ? note.getUser().getName() : null))
                .collect(Collectors.toList());
    }
}

