package be.ulb.ects.universityectssystem.dto;

import lombok.Data;

@Data
public class CourDto {
    private String mnemonique;
    private String intitule;
    private int credit;
    private String titulaire;
}
