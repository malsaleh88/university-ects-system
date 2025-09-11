package be.ulb.ects.universityectssystem.controller;

import be.ulb.ects.universityectssystem.model.Cour;
import be.ulb.ects.universityectssystem.repository.CourRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cour Controller", description = "Endpoints for managing cours")
@RestController
@RequestMapping("/api/cours")
public class CourController {
    private final CourRepository courRepository;

    public CourController(CourRepository courRepository) {
        this.courRepository = courRepository;
    }

    @Operation(summary = "Get all cours")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cours retrieved"),
            @ApiResponse(responseCode = "204", description = "No cours found")
    })
    @GetMapping
    public ResponseEntity<List<Cour>> getAllCours() {
        List<Cour> cours = courRepository.findAll();
        if (cours.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cours);
    }

    @Operation(summary = "Get cour by ID (mnemonique)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course found"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cour> getCour(@PathVariable String id) {
        return courRepository.findById(id)
                .map(ResponseEntity::ok) //
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
