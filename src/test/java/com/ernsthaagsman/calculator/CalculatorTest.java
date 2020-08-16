package com.ernsthaagsman.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    void isAlive(){
        assertEquals("2", calculator.calculateExpression("1 + 1"));
    }
}