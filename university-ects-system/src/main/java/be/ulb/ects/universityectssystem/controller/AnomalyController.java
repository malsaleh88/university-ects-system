package be.ulb.ects.universityectssystem.controller;

import be.ulb.ects.universityectssystem.dto.AnomalyDto;
import be.ulb.ects.universityectssystem.service.AnomalyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyController {

    private final AnomalyService anomalyService;

    public AnomalyController(AnomalyService anomalyService) {
        this.anomalyService = anomalyService;
    }

    @GetMapping
    public ResponseEntity<List<AnomalyDto>> getAllAnomalies() {
        return ResponseEntity.ok(anomalyService.generateAnomalies());
    }
}
