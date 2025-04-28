package com.craftteam.employee.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertFalse;


import com.craftteam.employee.model.Employee;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void shouldReturnEmployeesWithinSalaryRange() {
        List<Employee> employees = employeeService.getBySalaryRange(30000, 50000);
        assertFalse(employees.isEmpty());
    }
}

