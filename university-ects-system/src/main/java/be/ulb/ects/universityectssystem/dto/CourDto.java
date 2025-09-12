package be.ulb.ects.universityectssystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourDto {

    @JsonProperty("mnemonique")
    private String mnemonique;

    @JsonProperty("intitule")
    private String intitule;

    @JsonProperty("credit")
    private int credit;

    @JsonProperty("titulaire")
    private String titulaire;
}
