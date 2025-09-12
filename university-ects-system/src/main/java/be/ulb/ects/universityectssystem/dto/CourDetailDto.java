package be.ulb.ects.universityectssystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourDetailDto {
    private String mnemonique;
    private String intitule;
    private int credit;
    private String titulaire;
    private Integer note; // can be null if not presented
}
