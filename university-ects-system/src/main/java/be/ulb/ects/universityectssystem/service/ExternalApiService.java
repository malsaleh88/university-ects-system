package be.ulb.ects.universityectssystem.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;

    public ExternalApiService() {
        this.restTemplate = new RestTemplate();
    }

    public String testConnection() {
        String url = "https://b0s0kwos00g48ow8cg0skg4w.89.116.111.143.sslip.io/api";
        return restTemplate.getForObject(url, String.class);
    }
}
