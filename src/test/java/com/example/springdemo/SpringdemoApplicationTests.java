package com.example.springdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@ActiveProfiles("test")
class SpringdemoApplicationTests {
    @Autowired
    private EmployeeController controller;

    @Test
    void contextLoads() {
        List<Employee> employees = controller.all();
        assertThat(controller).isNotNull();
    }

}