package be.ulb.ects.universityectssystem.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "liste_notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private int id;

    private String matricule;   // FK to Student
    private String mnemonique;  // FK to Course
    private int note;           // grade
}
