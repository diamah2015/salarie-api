package com.craftteam.employee.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@JsonProperty("nom")  // correspondance avec le JSON existant
    private String lastName;

    @JsonProperty("prenom")
    private String firstName;

    @JsonProperty("date_naissance")
    private LocalDate birthDate;

    @JsonProperty("adresse")
    private String address;

    @JsonProperty("poste")
    private String position;

    @JsonProperty("genre")
    private String gender;

    @JsonProperty("salaire")
    private double salary;
}
