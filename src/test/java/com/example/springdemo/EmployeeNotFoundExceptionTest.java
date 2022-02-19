package com.example.springdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeNotFoundExceptionTest {
    @Mock
    private EmployeeNotFoundException exception;

    @Test
    void getMessage() {
        Mockito.when(exception.getMessage()).thenReturn("test");
        String msg = exception.getMessage();
        assertEquals("test", msg);
    }
}