package com.example.simplenotesapi.controller;

import com.example.simplenotesapi.model.Note;
import com.example.simplenotesapi.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@RequestBody Note note) {
        note.setCreatedAt(LocalDateTime.now());
        return noteRepository.save(note);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id) {
        Optional<Note> note = noteRepository.findById(id);

        if (note.isPresent()) {
            return ResponseEntity.ok(note.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String id) {
        noteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
