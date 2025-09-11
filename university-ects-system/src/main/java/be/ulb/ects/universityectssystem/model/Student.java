package be.ulb.ects.universityectssystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "liste_inscriptions")
public class Student {
    @Id
    private String matricule;
    private String nom;
    private String prenom;
    private int annee_etude;
    private String cours_json; // JSON string (list of course mnemonics)
}
