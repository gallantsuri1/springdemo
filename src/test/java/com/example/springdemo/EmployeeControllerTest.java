package com.example.springdemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {
    @Spy
    @InjectMocks
    private EmployeeController controller;

    @Mock
    private EmployeeRepository repository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void all() {

        List<Employee> expected = Arrays.asList(
            mock(Employee.class),
            mock(Employee.class));

        //when(controller.all()).thenReturn(expected);
        //when(controller.all()).();
        when(repository.findAll()).thenReturn(expected);
        List<Employee> actual = controller.all();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void get() {
        Employee expected = new Employee();
        expected.setId(123L);
        expected.setFirstName("John");
        expected.setLastName("Cena");
        expected.setRole("Manager");
        expected.setSalary(10000000);
        when(repository.findById(123L)).thenReturn(Optional.of(expected));

        Employee actual = controller.get(123L);
        assertEquals(expected, actual);

    }

    @Test
    void create() {
        Employee expected = new Employee();
        expected.setId(123L);
        expected.setFirstName("John");
        expected.setLastName("Cena");
        expected.setRole("Manager");
        expected.setSalary(10000000);
        when( repository.save(expected)).thenReturn(expected);
        Employee actual = controller.create(expected);

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        Employee expected = new Employee();
        expected.setId(123L);
        expected.setFirstName("Elon");
        expected.setLastName("Musk");
        expected.setRole("CEO");
        expected.setSalary(100000000);

        Function<Employee, Employee> function = employee -> {
            expected.setId(123L);
            expected.setFirstName("Elon");
            expected.setLastName("Musk");
            expected.setRole("CEO");
            expected.setSalary(100000000);
            return employee;
        };
        when(repository.save(expected)).thenReturn(expected);
        when(repository.findById(123L).map(function)).thenReturn(Optional.of(expected));
        Employee actual = controller.update(123L, expected);

        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        //doNothing().when(controller).delete(123L);
        doNothing().when(repository).deleteById(123L);

        controller.delete(123L);
        //verify(controller, times(1)).delete(123L);
    }

    @Test
    void notFound() {
        EmployeeNotFoundException ex = mock(EmployeeNotFoundException.class);
        when(ex.getMessage()).thenReturn("test");
        String msg="";
        try{
            controller.get(123L);
        } catch (EmployeeNotFoundException e) {
           msg = ex.getMessage();
        }
        assertEquals("test", msg);
    }

    @Test
    void advice() {
        EmployeeNotFoundException exception = mock(EmployeeNotFoundException.class);
        EmployeeNotFoundAdvice advice = new EmployeeNotFoundAdvice();
        when(exception.getMessage()).thenReturn("test");
        String msg = advice.notFound(exception);
        assertEquals("test", msg);
    }
}