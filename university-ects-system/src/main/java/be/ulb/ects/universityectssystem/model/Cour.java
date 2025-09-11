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
@Table(name = "liste_cours")
public class Cour {
    @Id
    private String mnemonique;
    private String intitule;
    private int credit;
    private String titulaire;
}
