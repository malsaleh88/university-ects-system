package be.ulb.ects.universityectssystem.dto;

import lombok.Data;

@Data
public class NoteDto {
    private int id;
    private String matricule;   // FK to student
    private String mnemonique;  // FK to course
    private Integer note;       // note can be null
}
