package com.ernsthaagsman.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    void basicAddition(){
        assertEquals("2.0", calculator.calculateExpression("1 + 1"));
    }

    @Test
    void basicMultiplication() {
        assertEquals("10.0", calculator.calculateExpression("5 * 2"));
    }
}