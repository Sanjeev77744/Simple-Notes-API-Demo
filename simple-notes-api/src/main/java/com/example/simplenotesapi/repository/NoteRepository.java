package com.example.simplenotesapi.repository;

import com.example.simplenotesapi.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {
}
