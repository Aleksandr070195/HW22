package com.example.hw22.service;

import com.example.hw22.Exception.EmployeeAlreadyAddedException;
import com.example.hw22.Exception.EmployeeNotFoundException;
import com.example.hw22.Exception.EmployeeStorageIsFullException;
import com.example.hw22.Exception.InvalidDataException;
import com.example.hw22.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        if (!StringUtils.isEmpty(employee.getFirstName())||!StringUtils.isEmpty(employee.getLasteName())) {

            throw new InvalidDataException();
        }
        if (employees.size() >= SIZE_LIMIT) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(creatKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(creatKey(employee), employee);
        return employee;
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get((firstName + " " + lastName).toLowerCase());
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Employee remove(String firstName, String lastName) {

        return employees.remove((firstName + " " + lastName).toLowerCase());
    }

    public String creatKey(Employee employee) {
        return (employee.getFirstName() + employee.getLasteName()).toLowerCase();
    }
}
