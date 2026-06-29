package com.huzaifah.task_manager;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private final List<Note> notes = new ArrayList<>();

    public NoteService(){
        notes.add(new Note(0L, "Clean the dishes", true));
        notes.add(new Note(1L, "Brush my teeth", false));
    }

    public List<NoteDTO> getAllNotes(){
        return notes.stream().map(note -> new NoteDTO(note.getId(), note.getContent()))
                .collect(Collectors.toList());
    }

    public NoteDTO getNoteById(Long id){
        for(Note note : notes){
            if (note.getId().equals(id)){
                return new NoteDTO(note.getId(), note.getContent());
            }
        }
        return null;
    }

    public NoteDTO createNote(Note note){
        notes.add(note);
        return new NoteDTO(note.getId(), note.getContent());
    }

    public boolean deleteNote(Long id){
        return notes.removeIf(note -> note.getId().equals(id));
    }

}

