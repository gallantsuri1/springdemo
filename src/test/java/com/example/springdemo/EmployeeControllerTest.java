package com.example.springdemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    private EmployeeController controller;

    @Mock
    private EmployeeRepository repository;

    @BeforeEach
    void setup() {
        controller = new EmployeeController(repository);
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
        expected.setId(123l);
        expected.setFirstName("John");
        expected.setLastName("Cena");
        expected.setRole("Manager");
        expected.setSalary(10000000);
        when(repository.findById(123l)).thenReturn(Optional.of(expected));

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

        Function<Employee, Employee> function = employee -> {
            expected.setId(123l);
            expected.setFirstName("Elon");
            expected.setLastName("Musk");
            expected.setRole("CEO");
            expected.setSalary(100000000);
            return employee;
        };
        when(repository.save(expected)).thenReturn(expected);
        when(repository.findById(123l).map(function)).thenReturn(Optional.of(expected));
        Employee actual = controller.update(123l, expected);

        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        //doNothing().when(controller).delete(123l);
        doNothing().when(repository).deleteById(123l);

        controller.delete(123l);
        //verify(controller, times(1)).delete(123l);
    }

    @Test
    void notFound() {
        EmployeeNotFoundException ex = mock(EmployeeNotFoundException.class);
        when(ex.getMessage()).thenReturn("test");
        String msg="";
        try{
            controller.get(123l);
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