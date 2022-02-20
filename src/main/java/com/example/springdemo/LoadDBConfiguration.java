package com.example.springdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDBConfiguration {
    private static final Logger log = LoggerFactory.getLogger(LoadDBConfiguration.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(createEmployee("john", "deer", "manager", 100000)));
            log.info("Preloading " + repository.save(createEmployee("thomas", "edison", "employee", 80000)));
        };
    }

    private Employee createEmployee(String firstName, String lastName, String role, int salary) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setRole(role);
        employee.setSalary(salary);
        return employee;
    }
}


