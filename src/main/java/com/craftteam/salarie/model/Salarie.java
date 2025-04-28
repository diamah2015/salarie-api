package com.craftteam.salarie.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salarie {
    private String nom;
    private String prenom;

    @JsonProperty("date_naissance")
    private LocalDate dateNaissance;

    private String adresse;
    private String poste;
    private String genre;
    private double salaire;
}
