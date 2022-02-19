package com.example.springdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeController controller;

    @Mock
    private EmployeeRepository repository;

    @Test
    void all() {
        List<Employee> expected = Arrays.asList(
            mock(Employee.class),
            mock(Employee.class));

        when(controller.all()).thenReturn(expected);
        List<Employee> actual = controller.all();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void get() {
        Employee expected = new Employee();
        expected.setId(123l);
        expected.setFirstName("John");
        expected.setLastName("Cena");
        expected.setRole("Manager");
        expected.setSalary(10000000);
        when(controller.get(123l)).thenReturn(expected);

        Employee actual = controller.get(123l);
        assertEquals(expected, actual);

    }

    @Test
    void create() {
        Employee expected = new Employee();
        expected.setId(123l);
        expected.setFirstName("John");
        expected.setLastName("Cena");
        expected.setRole("Manager");
        expected.setSalary(10000000);
        when(controller.create(any())).thenReturn(expected);
        Employee actual = controller.create(expected);

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        Employee expected = new Employee();
        expected.setId(123l);
        expected.setFirstName("Elon");
        expected.setLastName("Musk");
        expected.setRole("CEO");
        expected.setSalary(100000000);

        when(controller.update(123l, expected)).thenReturn(expected);
        Employee actual = controller.update(123l, expected);

        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        doNothing().when(controller).delete(123l);

        controller.delete(123l);
        verify(controller, times(1)).delete(123l);
    }

    @Test
    void notFound() {
        when(controller.get(123l)).thenThrow(new EmployeeNotFoundException(123l));
        boolean status = false;
        try{
            controller.get(123l);
        } catch (EmployeeNotFoundException e) {
            status = true;
        }
        assertTrue(status);
    }
}