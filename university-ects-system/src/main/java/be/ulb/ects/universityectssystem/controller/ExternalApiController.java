package be.ulb.ects.universityectssystem.controller;

import be.ulb.ects.universityectssystem.dto.InscriptionDto;
import be.ulb.ects.universityectssystem.dto.CourDto;
import be.ulb.ects.universityectssystem.dto.NoteDto;
import be.ulb.ects.universityectssystem.service.ExternalApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/external")
public class ExternalApiController {

    private final ExternalApiService externalApiService;

    public ExternalApiController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @GetMapping("/inscriptions")
    public ResponseEntity<List<InscriptionDto>> fetchInscriptions() {
        return ResponseEntity.ok(externalApiService.getInscriptions());
    }

    @GetMapping("/cours")
    public ResponseEntity<List<CourDto>> fetchCours() {
        return ResponseEntity.ok(externalApiService.getCours());
    }

    @GetMapping("/notes")
    public ResponseEntity<List<NoteDto>> fetchNotes() {
        return ResponseEntity.ok(externalApiService.getNotes());
    }
}
