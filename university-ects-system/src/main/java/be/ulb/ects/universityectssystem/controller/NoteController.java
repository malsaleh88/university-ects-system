package be.ulb.ects.universityectssystem.controller;

import be.ulb.ects.universityectssystem.model.Note;
import be.ulb.ects.universityectssystem.repository.NoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        if (notes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notes);
    }


    @GetMapping("/student/{matricule}")
    public ResponseEntity<List<Note>> getNotesByStudent(@PathVariable String matricule) {
        List<Note> notes = noteRepository.findByMatricule(matricule);
        if (notes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/course/{mnemonique}")
    public ResponseEntity<List<Note>> getNotesByCourse(@PathVariable String mnemonique) {
        List<Note> notes = noteRepository.findByMnemonique(mnemonique);
        if (notes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{matricule}/{mnemonique}")
    public ResponseEntity<List<Note>> getNoteForStudentAndCourse(
            @PathVariable String matricule,
            @PathVariable String mnemonique
    ) {
        List<Note> notes = noteRepository.findByMatriculeAndMnemonique(matricule, mnemonique);
        if (notes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notes);
    }
}
