package be.ulb.ects.universityectssystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionDto {
    private String matricule;
    private String nom;
    private String prenom;
    private int annee_etude;
    private String cours_json; // JSON string of courses
}
