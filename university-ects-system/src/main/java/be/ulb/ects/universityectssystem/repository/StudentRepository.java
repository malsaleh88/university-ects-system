package be.ulb.ects.universityectssystem.repository;

import be.ulb.ects.universityectssystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
