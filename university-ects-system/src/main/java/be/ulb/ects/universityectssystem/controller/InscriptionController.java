package be.ulb.ects.universityectssystem.controller;

import be.ulb.ects.universityectssystem.model.Inscription;
import be.ulb.ects.universityectssystem.repository.InscriptionRepository;
import be.ulb.ects.universityectssystem.service.InscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Inscription Controller", description = "Endpoints for managing inscriptions")
@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {

    private final InscriptionService inscriptionService;

    public InscriptionController(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }


    @Operation(summary = "Get all inscriptions")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "inscriptions retrieved"),
            @ApiResponse(responseCode = "204", description = "No inscriptions found")
    })
    @GetMapping
    public ResponseEntity<List<Inscription>> getAllInscriptions() {
        List<Inscription> inscriptions = inscriptionService.getAllInscriptions();
        if (inscriptions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inscriptions);
    }


    @Operation(summary = "Get inscription by matricule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "inscription found"),
            @ApiResponse(responseCode = "404", description = "inscription not found")
    })
    @GetMapping("/{matricule}")
    public ResponseEntity<List<Inscription>> getInscription(@PathVariable String matricule) {
        List<Inscription> inscriptions = inscriptionService.getInscriptionsByMatricule(matricule);
        if (inscriptions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inscriptions);
    }

}
