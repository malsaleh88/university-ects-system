package be.ulb.ects.universityectssystem.controller;

import be.ulb.ects.universityectssystem.model.Note;
import be.ulb.ects.universityectssystem.repository.NoteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Note Controller", description = "Endpoints for managing notes")
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Operation(summary = "Get all notes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notes retrieved"),
            @ApiResponse(responseCode = "204", description = "No notes found")
    })
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        if (notes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notes);
    }

    @Operation(summary = "Get notes for a student")
    @GetMapping("/student/{matricule}")
    public ResponseEntity<List<Note>> getNotesByStudent(@PathVariable String matricule) {
        List<Note> notes = noteRepository.findByMatricule(matricule);
        if (notes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notes);
    }

    @Operation(summary = "Get notes for a course")
    @GetMapping("/course/{mnemonique}")
    public ResponseEntity<List<Note>> getNotesByCourse(@PathVariable String mnemonique) {
        List<Note> notes = noteRepository.findByMnemonique(mnemonique);
        if (notes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notes);
    }

    @Operation(summary = "Get note for a student in a course")
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
