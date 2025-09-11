package be.ulb.ects.universityectssystem.controller;

import be.ulb.ects.universityectssystem.model.Course;
import be.ulb.ects.universityectssystem.repository.CourseRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Operation(summary = "Get all courses")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Courses retrieved"),
            @ApiResponse(responseCode = "204", description = "No courses found")
    })
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(courses);
    }

    @Operation(summary = "Get course by ID (mnemonique)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course found"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable String id) {
        return courseRepository.findById(id)
                .map(ResponseEntity::ok) //
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
