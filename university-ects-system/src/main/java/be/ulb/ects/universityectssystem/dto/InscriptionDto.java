package be.ulb.ects.universityectssystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InscriptionDto {
    private String matricule;
    private String nom;
    private String prenom;

    @JsonProperty("annee_etude")
    private int anneeEtude;

    @JsonProperty("cours_json")
    private String coursJson;
}
