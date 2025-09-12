package be.ulb.ects.universityectssystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulletinDto {
    private String matricule;
    private String nom;
    private String prenom;
    private int annee;
    private int ectsTotalInscrits;
    private int ectsObtenus;
    private double moyennePonderee;
    private boolean reussite;
    private List<CourDetailDto> details;
}