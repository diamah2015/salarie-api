package com.craftteam.employee.service;

import org.springframework.stereotype.Service;

import com.craftteam.employee.model.Employee;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private List<Employee> employee;

    // Setter utilisé par le DataLoader pour injecter la liste initiale
    public void setSalaries(List<Employee> employee) {
        this.employee = employee;
    }

    // 1. Tous les salariés
    public List<Employee> getAll() {
        return employee;
    }

    // 2. Salariés triés par nom (ordre alphabétique)
    public List<Employee> getAllSortedByName() {
        return employee.stream()
                .sorted((s1, s2) -> s1.getFirstName().compareToIgnoreCase(s2.getLastName()))
                .collect(Collectors.toList());
    }

    // 3. Salariés triés par date de naissance (ordre croissant)
    public List<Employee> getAllSortedByBirthDate() {
        return employee.stream()
                .sorted((s1, s2) -> s1.getBirthDate().compareTo(s2.getBirthDate()))
                .collect(Collectors.toList());
    }

    // 4. Salariés par genre (Homme ou Femme)
    public List<Employee> getByGender(String gender) {
        return employee.stream()
                .filter(s -> s.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }

    // 5. Salariés par intervalle de salaire
    public List<Employee> getBySalaryRange(double min, double max) {
        return employee.stream()
                .filter(s -> s.getSalary() >= min && s.getSalary() <= max)
                .collect(Collectors.toList());
    }
}
