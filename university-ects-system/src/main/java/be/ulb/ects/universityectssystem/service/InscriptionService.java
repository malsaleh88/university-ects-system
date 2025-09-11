package be.ulb.ects.universityectssystem.service;

import be.ulb.ects.universityectssystem.repository.InscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    public InscriptionService(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }
    
}
