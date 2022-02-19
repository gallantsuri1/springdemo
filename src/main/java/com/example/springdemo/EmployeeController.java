package com.example.springdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class EmployeeController {
    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository){
        this.repository = repository;
    }

    @GetMapping("/employees")
    List<Employee> all() {
        log.info("all method invoked");
        return repository.findAll();
    }

    @GetMapping("/employees/{id}")
    Employee get(@PathVariable Long id) {
        log.info("get method invoked");
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PostMapping("/employees")
    Employee create(@RequestBody Employee employee) {
        log.info("create method invoked");
        return repository.save(employee);
    }

    @PutMapping("/employees/{id}")
    Employee update(@RequestParam Long id, @RequestBody Employee employee) {
        log.info("update method invoked");
        return repository.findById(id)
                .map(employee1 -> {
                    employee1.setFirstName(employee.getFirstName());
                    employee1.setLastName(employee.getLastName());
                    employee1.setSalary(employee.getSalary());
                    employee1.setRole(employee.getRole());
                    return repository.save(employee1);
                }).orElseGet(() -> {
                    employee.setId(id);
                    return repository.save(employee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void delete(@PathVariable Long id) {
        log.info("delete method invoked");
        repository.deleteById(id);
    }
}










