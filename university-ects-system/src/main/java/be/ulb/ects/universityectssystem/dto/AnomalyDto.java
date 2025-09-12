package be.ulb.ects.universityectssystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnomalyDto {
    private String type;      // e.g. NOTE_SANS_INSCRIPTION
    private String matricule; // student id
    private int annee;        // year of study
    private String detail;    // explanation
}
