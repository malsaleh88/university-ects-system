package be.ulb.ects.universityectssystem.repository;

import be.ulb.ects.universityectssystem.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, String> {
    List<Inscription> findByMatricule(String matricule);

}
