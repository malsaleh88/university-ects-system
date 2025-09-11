package be.ulb.ects.universityectssystem.controller;

import be.ulb.ects.universityectssystem.model.Student;
import be.ulb.ects.universityectssystem.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }


    @GetMapping("/{matricule}")
    public ResponseEntity<List<Student>> getStudent(@PathVariable String matricule) {
        List<Student> students = studentRepository.findByMatricule(matricule);
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students); 
    }

}
