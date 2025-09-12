package be.ulb.ects.universityectssystem.controller;

import be.ulb.ects.universityectssystem.dto.BulletinDto;
import be.ulb.ects.universityectssystem.service.BulletinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bulletins")
public class BulletinController {

    private final BulletinService bulletinService;

    public BulletinController(BulletinService bulletinService) {
        this.bulletinService = bulletinService;
    }

    @GetMapping
    public ResponseEntity<List<BulletinDto>> getAllBulletins() {
        return ResponseEntity.ok(bulletinService.generateBulletins());
    }
}
