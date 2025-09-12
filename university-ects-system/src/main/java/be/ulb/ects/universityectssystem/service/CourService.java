package be.ulb.ects.universityectssystem.service;

import be.ulb.ects.universityectssystem.repository.CourRepository;
import org.springframework.stereotype.Service;

@Service

public class CourService {

    private final CourRepository courRepository;

    public CourService(CourRepository courRepository) {
        this.courRepository = courRepository;
    }
    

}
