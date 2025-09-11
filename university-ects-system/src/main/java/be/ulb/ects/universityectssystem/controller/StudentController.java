package be.ulb.ects.universityectssystem.controller;

import be.ulb.ects.universityectssystem.model.Student;
import be.ulb.ects.universityectssystem.repository.StudentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Get all students")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Students retrieved"),
            @ApiResponse(responseCode = "204", description = "No students found")
    })
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }


    @Operation(summary = "Get student by matricule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student found"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/{matricule}")
    public ResponseEntity<List<Student>> getStudent(@PathVariable String matricule) {
        List<Student> students = studentRepository.findByMatricule(matricule);
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

}
