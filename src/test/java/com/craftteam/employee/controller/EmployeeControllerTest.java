package com.craftteam.employee.controller;


import com.craftteam.employee.model.Employee;
import com.craftteam.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void shouldReturnAllEmployees() throws Exception {
        // Prepare test data
        Employee employee1 = new Employee("Dupont", "Jean", LocalDate.of(1985, 3, 15), "Paris", "Developer", "Male", 40000);
        Employee employee2 = new Employee("Martin", "Sophie", LocalDate.of(1990, 7, 22), "Paris", "Analyst", "Female", 38000);

        given(employeeService.getAll()).willReturn(Arrays.asList(employee1, employee2));

        // Call the API and verify
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nom").value("Dupont"))
                .andExpect(jsonPath("$[1].prenom").value("Sophie"));

    }
    
    
    @Test
    void shouldReturnEmployeesSortedByName() throws Exception {
        Employee employee1 = new Employee("Martin", "Sophie", LocalDate.of(1990, 7, 22), "Paris", "Analyst", "Female", 38000);
        Employee employee2 = new Employee("Dupont", "Jean", LocalDate.of(1985, 3, 15), "Paris", "Developer", "Male", 40000);

        given(employeeService.getAllSortedByName()).willReturn(Arrays.asList(employee2, employee1));

        mockMvc.perform(get("/api/employees/sorted/name"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nom").value("Dupont"))
                .andExpect(jsonPath("$[1].nom").value("Martin"));
    }
    
    @Test
    void shouldReturnEmployeesSortedByBirthDate() throws Exception {
        Employee employee1 = new Employee("Dupont", "Jean", LocalDate.of(1985, 3, 15), "Paris", "Developer", "Male", 40000);
        Employee employee2 = new Employee("Martin", "Sophie", LocalDate.of(1990, 7, 22), "Paris", "Analyst", "Female", 38000);

        given(employeeService.getAllSortedByBirthDate()).willReturn(Arrays.asList(employee1, employee2));

        mockMvc.perform(get("/api/employees/sorted/birthdate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nom").value("Dupont"))
                .andExpect(jsonPath("$[1].nom").value("Martin"));
    }

    
    @Test
    void shouldReturnEmployeesByGender() throws Exception {
        Employee employee = new Employee("Martin", "Sophie", LocalDate.of(1990, 7, 22), "Paris", "Analyst", "Female", 38000);

        given(employeeService.getByGender("Female")).willReturn(Arrays.asList(employee));

        mockMvc.perform(get("/api/employees/gender/Female"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].prenom").value("Sophie"));
    }
    
    
    @Test
    void shouldReturnEmployeesBySalaryRange() throws Exception {
        Employee employee1 = new Employee("Dupont", "Jean", LocalDate.of(1985, 3, 15), "Paris", "Developer", "Male", 40000);
        Employee employee2 = new Employee("Martin", "Sophie", LocalDate.of(1990, 7, 22), "Paris", "Analyst", "Female", 38000);

        given(employeeService.getBySalaryRange(30000, 50000)).willReturn(Arrays.asList(employee1, employee2));

        mockMvc.perform(get("/api/employees/salary?min=30000&max=50000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].salaire").value(40000.0))
                .andExpect(jsonPath("$[1].salaire").value(38000.0));
    }

    
    
    
    


    
    
    
    
}
