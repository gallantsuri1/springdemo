package com.example.springdemo;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Long id) {
        super("No Employee details found with id: "+id);
    }
}