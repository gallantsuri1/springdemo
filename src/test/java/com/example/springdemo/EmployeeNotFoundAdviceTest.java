package com.example.springdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeNotFoundAdviceTest {
    @Mock
    private EmployeeNotFoundAdvice advice;

    @Mock
    private EmployeeNotFoundException exception;

    @Test
    void testAdvice() {
        EmployeeNotFoundException e = new EmployeeNotFoundException(123l);
        when(advice.notFound(e)).thenReturn(e.getMessage());
        String a = advice.notFound(e);

        assertEquals("No Employee details found with id: 123", a);

    }

    @Test
    void testException() {
        EmployeeNotFoundException e = new EmployeeNotFoundException(123l);
        when(exception.getMessage()).thenReturn(e.getMessage());
        String msg = exception.getMessage();
        assertEquals("No Employee details found with id: 123", msg);
    }

}