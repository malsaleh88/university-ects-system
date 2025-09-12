package be.ulb.ects.universityectssystem.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    private static final String BASE_URL = "https://b0s0kwos00g48ow8cg0skg4w.89.116.111.143.sslip.io";

    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getInscriptions() {
        String url = BASE_URL + "/inscriptions"; // ✅ FIXED
        return restTemplate.getForObject(url, String.class);
    }

    public String getCours() {
        String url = BASE_URL + "/cours"; // ✅ FIXED
        return restTemplate.getForObject(url, String.class);
    }

    public String getNotes() {
        String url = BASE_URL + "/notes"; // ✅ FIXED
        return restTemplate.getForObject(url, String.class);
    }
}
