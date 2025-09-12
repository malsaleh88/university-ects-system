package be.ulb.ects.universityectssystem.service;

import be.ulb.ects.universityectssystem.dto.CourDto;
import be.ulb.ects.universityectssystem.dto.InscriptionDto;
import be.ulb.ects.universityectssystem.dto.NoteDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ExternalApiService {

    private static final String BASE_URL = "https://b0s0kwos00g48ow8cg0skg4w.89.116.111.143.sslip.io";

    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<InscriptionDto> getInscriptions() {
        String url = BASE_URL + "/inscriptions";
        InscriptionDto[] response = restTemplate.getForObject(url, InscriptionDto[].class);
        return Arrays.asList(response);
    }

    public List<CourDto> getCours() {
        String url = BASE_URL + "/cours";
        CourDto[] response = restTemplate.getForObject(url, CourDto[].class);
        return Arrays.asList(response);
    }

    public List<NoteDto> getNotes() {
        String url = BASE_URL + "/notes";
        NoteDto[] response = restTemplate.getForObject(url, NoteDto[].class);
        return Arrays.asList(response);
    }
}
