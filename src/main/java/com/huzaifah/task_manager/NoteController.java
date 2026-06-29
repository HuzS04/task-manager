package com.huzaifah.task_manager;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public String getNotes(){
        return "Here are your notes";
    }

    @GetMapping("/home")
    public String home(){
        return "Welcome to your notes manager";
    }

    @GetMapping("/notes/all")
    public List<NoteDTO> getAllNotes(){
        return noteService.getAllNotes();
    }

    @GetMapping("/notes/{id}")
    public NoteDTO getNoteById(@PathVariable Long id){
        return noteService.getNoteById(id);
    }

    @PostMapping("/notes")
    public NoteDTO createNote(@RequestBody Note note){
        return noteService.createNote(note);
    }

    @DeleteMapping("/notes/{id}")
    public boolean deleteNote(@PathVariable Long id){
        return noteService.deleteNote(id);
    }
}
