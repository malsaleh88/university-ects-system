package be.ulb.ects.universityectssystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourDto {
    private String mnemonique;
    private String intitule;
    private int credit;
    private String titulaire;
}
