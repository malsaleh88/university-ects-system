package be.ulb.ects.universityectssystem.service;

import be.ulb.ects.universityectssystem.model.Note;
import be.ulb.ects.universityectssystem.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public List<Note> getNotesByStudent(String matricule) {
        return noteRepository.findByMatricule(matricule);
    }

    public List<Note> getNotesByCour(String mnemonique) {
        return noteRepository.findByMnemonique(mnemonique);
    }


}
