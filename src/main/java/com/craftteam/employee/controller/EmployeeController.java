package com.craftteam.employee.controller;

import org.springframework.web.bind.annotation.*;

import com.craftteam.employee.model.Employee;
import com.craftteam.employee.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	  private final EmployeeService employeeService;

	    public EmployeeController(EmployeeService employeeService) {
	        this.employeeService = employeeService;
	    }

	    // 1. Tous les salariés
	    @GetMapping
	    public List<Employee> getAll() {
	        return employeeService.getAll();
	    }

	    // 2. Trié par nom
	    @GetMapping("/sorted/name")
	    public List<Employee> getAllSortedByName() {
	        return employeeService.getAllSortedByName();
	    }

	    // 3. Trié par date de naissance
	    @GetMapping("/sorted/birthdate")
	    public List<Employee> getAllSortedByBirthDate() {
	        return employeeService.getAllSortedByBirthDate();
	    }

	    // 4. Filtrer par genre
	    @GetMapping("/gender/{gender}")
	    public List<Employee> getByGender(@PathVariable String gender) {
	        return employeeService.getByGender(gender);
	    }

	    // 5. Filtrer par intervalle de salaire
	    @GetMapping("/salary")
	    public List<Employee> getBySalaryRange(@RequestParam double min, @RequestParam double max) {
	        return employeeService.getBySalaryRange(min, max);
	    }
}
