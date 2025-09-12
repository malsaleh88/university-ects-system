package be.ulb.ects.universityectssystem.dto;

import lombok.Data;

@Data
public class InscriptionDto {
    private String matricule;
    private String nom;
    private String prenom;
    private int annee_etude;
    private String cours_json; // JSON string of courses
}
