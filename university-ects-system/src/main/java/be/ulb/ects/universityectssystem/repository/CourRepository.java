package be.ulb.ects.universityectssystem.repository;

import be.ulb.ects.universityectssystem.model.Cour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourRepository extends JpaRepository<Cour, String> {
}
