package be.ulb.ects.universityectssystem.controller;

import be.ulb.ects.universityectssystem.model.Student;
import be.ulb.ects.universityectssystem.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

}
