package be.ulb.ects.universityectssystem.service;

import be.ulb.ects.universityectssystem.model.Cour;
import be.ulb.ects.universityectssystem.repository.CourRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CourService {

    private final CourRepository courRepository;

    public CourService(CourRepository courRepository) {
        this.courRepository = courRepository;
    }

    public List<Cour> getAllCours() {
        return courRepository.findAll();
    }


}
