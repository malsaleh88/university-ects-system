package be.ulb.ects.universityectssystem.service;

import be.ulb.ects.universityectssystem.model.Inscription;
import be.ulb.ects.universityectssystem.repository.InscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    public InscriptionService(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    public List<Inscription> getAllInscriptions() {
        return inscriptionRepository.findAll();
    }


}
