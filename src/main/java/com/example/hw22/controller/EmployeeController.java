package com.example.hw22.controller;
import com.example.hw22.model.Employee;
import com.example.hw22.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/all")
    public Collection<Employee> all(){
        return employeeService.getAll();
    }
    @RequestMapping("/add")
    public Employee add(String firstName, String lastName, Integer department, double salary){
        return employeeService.add(new Employee(firstName, lastName, department, salary));
    }
    @RequestMapping("/remove")
    public Employee remove(String firstName, String lastName){
        return employeeService.remove(firstName, lastName);
    }
    @RequestMapping("/find")
    public Employee find(String firstName, String lastName){
        return employeeService.find(firstName, lastName);
    }

}
