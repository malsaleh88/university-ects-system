package be.ulb.ects.universityectssystem.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;

    public ExternalApiService() {
        this.restTemplate = new RestTemplate();
    }

    
}
