package com.craftteam.salarie.service;

import com.craftteam.salarie.model.Salarie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalarieService {

    private List<Salarie> salaries;

    // Setter utilisé par le DataLoader pour injecter la liste initiale
    public void setSalaries(List<Salarie> salaries) {
        this.salaries = salaries;
    }

    // 1. Tous les salariés
    public List<Salarie> getAll() {
        return salaries;
    }

    // 2. Salariés triés par nom (ordre alphabétique)
    public List<Salarie> getAllSortedByName() {
        return salaries.stream()
                .sorted((s1, s2) -> s1.getNom().compareToIgnoreCase(s2.getNom()))
                .collect(Collectors.toList());
    }

    // 3. Salariés triés par date de naissance (ordre croissant)
    public List<Salarie> getAllSortedByBirthDate() {
        return salaries.stream()
                .sorted((s1, s2) -> s1.getDateNaissance().compareTo(s2.getDateNaissance()))
                .collect(Collectors.toList());
    }

    // 4. Salariés par genre (Homme ou Femme)
    public List<Salarie> getByGenre(String genre) {
        return salaries.stream()
                .filter(s -> s.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    // 5. Salariés par intervalle de salaire
    public List<Salarie> getBySalaireRange(double min, double max) {
        return salaries.stream()
                .filter(s -> s.getSalaire() >= min && s.getSalaire() <= max)
                .collect(Collectors.toList());
    }
}
