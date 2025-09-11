package be.ulb.ects.universityectssystem.controller;

import be.ulb.ects.universityectssystem.model.Student;
import be.ulb.ects.universityectssystem.repository.StudentRepository;
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
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{matricule}")
    public List<Student> getStudent(@PathVariable String matricule) {
        return studentRepository.findByMatricule(matricule);
    }

}
