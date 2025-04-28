package com.craftteam.employee.config;


import com.craftteam.employee.model.Employee;
import com.craftteam.employee.service.EmployeeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader {

    private final EmployeeService employeeService;

    public DataLoader(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadSalaries() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // <<<<<< IMPORTANT
        
        InputStream inputStream = getClass().getResourceAsStream("/salarie.json");
        List<Employee> salaries = mapper.readValue(inputStream, new TypeReference<List<Employee>>() {});
        employeeService.setSalaries(salaries);
    }
}
