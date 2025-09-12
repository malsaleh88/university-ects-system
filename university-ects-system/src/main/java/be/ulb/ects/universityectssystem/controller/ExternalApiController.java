package be.ulb.ects.universityectssystem.controller;

import be.ulb.ects.universityectssystem.service.ExternalApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/external")
public class ExternalApiController {

    private final ExternalApiService externalApiService;

    public ExternalApiController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @GetMapping("/inscriptions")
    public String fetchInscriptions() {
        return externalApiService.getInscriptions();
    }

    @GetMapping("/cours")
    public String fetchCours() {
        return externalApiService.getCours();
    }

    @GetMapping("/notes")
    public String fetchNotes() {
        return externalApiService.getNotes();
    }
}
