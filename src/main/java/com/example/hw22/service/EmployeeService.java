package com.example.hw22.service;

import com.example.hw22.Exception.EmployeeAlreadyAddedException;
import com.example.hw22.Exception.EmployeeNotFoundException;
import com.example.hw22.Exception.EmployeeStorageIsFullException;
import com.example.hw22.model.Employee;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>(SIZE_LIMIT);
    private static final int SIZE_LIMIT = 5;

    public Collection<Employee> getAll() {
        return employees.values();
    }

    public Employee add(Employee employee) {
       if (employees.size()>= SIZE_LIMIT){
           throw new EmployeeStorageIsFullException();
       }
       if (employees.containsKey(creatKey(employee))){
           throw new EmployeeAlreadyAddedException();
       }
       employees.put(creatKey(employee), employee);
       return employee;
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(creatKey(firstName, lastName).toLowerCase());
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Employee remove(String firstName, String lastName) {

        return employees.remove(creatKey(firstName, lastName).toLowerCase());
    }

    public static @NotNull String creatKey(Employee employee) {
        return (employee.getFirstName() + employee.getLasteName());
    }
    private static String creatKey(String firstName, String lastName){
        return (firstName + lastName).toLowerCase();
    }
}
