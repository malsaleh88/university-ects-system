package be.ulb.ects.universityectssystem.controller;

import be.ulb.ects.universityectssystem.model.Course;
import be.ulb.ects.universityectssystem.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable String id) {
        return courseRepository.findById(id).orElse(null);
    }
}
