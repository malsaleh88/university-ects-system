package be.ulb.ects.universityectssystem.repository;

import be.ulb.ects.universityectssystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
}
