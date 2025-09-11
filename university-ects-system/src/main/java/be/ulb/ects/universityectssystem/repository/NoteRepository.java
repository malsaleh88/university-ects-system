package be.ulb.ects.universityectssystem.repository;

import be.ulb.ects.universityectssystem.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByMatricule(String matricule);

}
