package com.example.springdemo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Employee implements Serializable {
    private @Id
    @GeneratedValue
    Long id;
    private String firstName;
    private String lastName;
    private String role;
    private int salary;
}