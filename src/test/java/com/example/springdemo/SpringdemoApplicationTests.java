package com.example.springdemo;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
//@ActiveProfiles("test")
class SpringdemoApplicationTests {
    @Autowired
    private EmployeeController controller;

    @Test
    void contextLoads() {
        List<Employee> employees = controller.all();
        assertThat(employees).isNotNull();
    }

    @Test
    void main() {
        MockedStatic<SpringApplication> mock1 = mockStatic(SpringApplication.class);
        mock1.when(() -> SpringApplication.run(SpringdemoApplication.class, null))
                .thenAnswer((Answer<Void>) invocation -> null);
        SpringdemoApplication.main(null);
    }

}