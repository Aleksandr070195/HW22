package com.example.hw22.service;

import com.example.hw22.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;
    List<Employee> employees = Arrays.asList(
            new Employee("Aleksandr", "Petrov", 1, 100),
            new Employee("Nikita", "Ivanov", 1, 200),
            new Employee("Aleksey", "Alekseev", 2, 150),
            new Employee("Petr", "Popov", 3, 250),
            new Employee("Alina", "Sokolova", 2, 350)
    );
    @BeforeEach
    void setup(){
        when(employeeService.getAll()).thenReturn(employees);
    }
    @Test
    void test(){
        double actual = departmentService.getEmployeesSalarySum(2);
        assertEquals(500, actual, 0.000001);
    }
    @Test
    void min(){
        double actual = departmentService.getEmployeesSalarySum(1);
        assertEquals(300, actual, 0.000001);
    }
    @Test
    void max(){
        double actual = departmentService.getEmployeesSalarySum(1);
        assertEquals(300, actual, 0.000001);
    }
    @Test
    void getAlDepartment(){
        List<Employee> actual = departmentService.getAll(2);
        List<Employee> expected = Arrays.asList(
                new Employee("Aleksey", "Alekseev", 2, 150),
                new Employee("Alina", "Sokolova", 2, 350));
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
    }
    @Test
    void getAll(){
        List<Integer> exceptedDepartments = employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .collect(Collectors.toList());
        Map<Integer, List<Employee>> actual = departmentService.getAll();
        assertEquals(exceptedDepartments.size(), actual.keySet().size());
        assertTrue(exceptedDepartments.containsAll(actual.keySet()));
    }
}