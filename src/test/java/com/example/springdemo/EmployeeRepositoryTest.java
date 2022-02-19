package com.example.springdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeRepositoryTest {

    @Mock
    private EmployeeRepository repository;

    @Test
    void findAll() {
        List<Employee> l = new ArrayList<>();
        when(repository.findAll()).thenReturn(l);
        List<Employee> actual = repository.findAll();
        assertEquals(0, actual.size());
    }

    @Test
    void get() {
        when(repository.getById(123l)).thenReturn(any());
        Employee actual = repository.getById(123l);
        assertEquals(null, actual);
    }

    @Test
    void create() {
        Employee expected = new Employee();
        when(repository.save(expected)).thenReturn(expected);
        Employee actual = repository.save(expected);
        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        doNothing().when(repository).deleteById(123l);
        repository.deleteById(123l);
        verify(repository, times(1)).deleteById(123l);
    }

}