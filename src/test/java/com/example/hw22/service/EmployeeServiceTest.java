package com.example.hw22.service;

import com.example.hw22.Exception.EmployeeAlreadyAddedException;
import com.example.hw22.Exception.EmployeeNotFoundException;
import com.example.hw22.Exception.EmployeeStorageIsFullException;
import com.example.hw22.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
  EmployeeService employeeService = new EmployeeService();
    @Test
    public void getAll(){
        Employee e1 = new Employee("Aleksandr", "Petrov", 1, 20000.0);
        Employee e2 = new Employee("Nikita", "Ivanov", 2, 30000.0);
        employeeService.add(e1);
        employeeService.add(e2);
        List<Employee> excepted = Arrays.asList(e1, e2);
        assertEquals(2, employeeService.getAll().size());
        assertIterableEquals(excepted, employeeService.getAll());
    }
    @Test
    void add(){
        int lastSize = employeeService.getAll().size();
        Employee e1 = new Employee("Aleksandr", "Petrov", 1, 30000.0);
        employeeService.add(e1);
        assertEquals(lastSize + 1, employeeService.getAll().size());
        assertTrue(employeeService.getAll().contains(e1));
    }
    @Test
    void addExceptionDuplicate(){
        Employee e1 = new Employee("Aleksandr", "Petrov", 1, 20000.0);
        assertDoesNotThrow(()-> employeeService.add(e1));
        assertThrows(EmployeeAlreadyAddedException.class, ()-> employeeService.add(e1));

    }
    @Test
    void fullStorageException(){
        for (int i = 0; i < 5; i++) {
            Employee e = new Employee("name " + i, "lastname " + i, 1, 100);
            assertDoesNotThrow(() -> employeeService.add(e));
        }
        assertThrows(EmployeeStorageIsFullException.class,
                ()-> employeeService.add(new Employee("Aleksandr", "Petrov", 1, 10000.0)));
    }
    @Test
    void findPositive(){
        Employee expected = (new Employee("Aleksandr", "Petrov", 2, 20000.0));
        employeeService.add(expected);
        Employee actual = employeeService.find("Aleksandr", "Petrov");
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
    @Test
    void findNegative(){
        Employee expected = (new Employee("Aleksandr", "Petrov", 5, 60000.0));
        employeeService.add(expected);
        assertThrows(EmployeeNotFoundException.class, ()-> employeeService.find("Nikita", "Ivanov"));
    }
    @Test
    void remove(){
        Employee e = new Employee("Aleksandr", "Petrov", 1, 10000.0);
        employeeService.add(e);

        assertTrue(employeeService.getAll().contains(e));
        employeeService.remove("Aleksandr", "Petrov");
        assertFalse(employeeService.getAll().contains(e));
    }
}
