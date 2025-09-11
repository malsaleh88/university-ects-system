package be.ulb.ects.universityectssystem.repository;

import be.ulb.ects.universityectssystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByMatricule(String matricule);

}
