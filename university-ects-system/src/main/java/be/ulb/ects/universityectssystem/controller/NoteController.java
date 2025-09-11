package be.ulb.ects.universityectssystem.controller;

import be.ulb.ects.universityectssystem.model.Note;
import be.ulb.ects.universityectssystem.repository.NoteRepository;
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
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @GetMapping("/student/{matricule}")
    public List<Note> getNotesByStudent(@PathVariable String matricule) {
        return noteRepository.findByMatricule(matricule);
    }

    @GetMapping("/course/{mnemonique}")
    public List<Note> getNotesByCourse(@PathVariable String mnemonique) {
        return noteRepository.findByMnemonique(mnemonique);
    }

    @GetMapping("/{matricule}/{mnemonique}")
    public List<Note> getNoteForStudentAndCourse(
            @PathVariable String matricule,
            @PathVariable String mnemonique
    ) {
        return noteRepository.findByMatriculeAndMnemonique(matricule, mnemonique);
    }
}
