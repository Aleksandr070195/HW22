package com.example.hw22.model;



import org.springframework.util.StringUtils;

import java.util.Objects;

public class Employee {
    private String firstName;
    private  String LasteName;
    private Integer department;
    private double salary;

    public Employee(String firstName, String lasteName, Integer department, double salary) {
        this.firstName = StringUtils.capitalize(firstName.toLowerCase());
        LasteName = StringUtils.capitalize(lasteName.toLowerCase());
        this.department = department;
        this.salary = salary;
    }

    public Employee(String firstName, String lasteName){
        this.firstName = firstName;
        this.LasteName = lasteName;

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLasteName(String lasteName) {
        LasteName = lasteName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLasteName() {
        return LasteName;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getFullName(){
        return (firstName + LasteName).toLowerCase();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(LasteName, employee.LasteName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, LasteName);
    }
}
