package com.ernsthaagsman.calculator.interpreter;

import com.ernsthaagsman.calculator.ast.*;
import com.ernsthaagsman.calculator.tokenizer.MathToken;
import com.ernsthaagsman.calculator.tokenizer.MathTokenType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathInterpreterTest {
    @Test
    void testNumber() throws Exception {
        // Arrange
        AstNode root = new NumberNode(new MathToken(MathTokenType.NUMBER, "1"));

        // Act
        double result = MathInterpreter.interpret(root);

        // Assert
        assertEquals(1.00, result);
    }

    @Test
    void testAddition() throws Exception {
        // Arrange
        AstNode left = new NumberNode(new MathToken(MathTokenType.NUMBER, "1"));
        AstNode right = new NumberNode(new MathToken(MathTokenType.NUMBER, "1"));
        AstNode root = new AdditionNode(new MathToken(MathTokenType.PLUS, "+"), left, right);

        // Act
        double result = MathInterpreter.interpret(root);

        // Assert
        assertEquals(2.00, result);
    }

    @Test
    void testSubtraction() throws Exception {
        // Arrange
        AstNode left = new NumberNode(new MathToken(MathTokenType.NUMBER, "1"));
        AstNode right = new NumberNode(new MathToken(MathTokenType.NUMBER, "1"));
        AstNode root = new SubtractionNode(new MathToken(MathTokenType.MINUS, "-"), left, right);

        // Act
        double result = MathInterpreter.interpret(root);

        // Assert
        assertEquals(0.00, result);
    }

    @Test
    void testMultiplication() throws Exception {
        // Arrange
        AstNode left = new NumberNode(new MathToken(MathTokenType.NUMBER, "2"));
        AstNode right = new NumberNode(new MathToken(MathTokenType.NUMBER, "5"));
        AstNode root = new MultiplicationNode(new MathToken(MathTokenType.MULTIPLY, "*"), left, right);

        // Act
        double result = MathInterpreter.interpret(root);

        // Assert
        assertEquals(10.00, result);
    }

    @Test
    void testDecimalMultiplication() throws Exception {
        // Arrange
        AstNode left = new NumberNode(new MathToken(MathTokenType.NUMBER, "1.5"));
        AstNode right = new NumberNode(new MathToken(MathTokenType.NUMBER, "5"));
        AstNode root = new MultiplicationNode(new MathToken(MathTokenType.MULTIPLY, "*"), left, right);

        // Act
        double result = MathInterpreter.interpret(root);

        // Assert
        assertEquals(7.5, result);
    }

    @Test
    void testDivision() throws Exception {
        // Arrange
        AstNode left = new NumberNode(new MathToken(MathTokenType.NUMBER, "6"));
        AstNode right = new NumberNode(new MathToken(MathTokenType.NUMBER, "2"));
        AstNode root = new DivisionNode(new MathToken(MathTokenType.DIVIDE, "/"), left, right);

        // Act
        double result = MathInterpreter.interpret(root);

        // Assert
        assertEquals(3.00, result);
    }

    @Test
    void testNested() throws Exception {
        // Arrange
        AstNode add_left = new NumberNode(new MathToken(MathTokenType.NUMBER, "2"));
        AstNode add_right = new NumberNode(new MathToken(MathTokenType.NUMBER, "4"));
        AstNode left = new AdditionNode(new MathToken(MathTokenType.PLUS, "+"), add_left, add_right);
        AstNode right = new NumberNode(new MathToken(MathTokenType.NUMBER, "2"));
        AstNode root = new DivisionNode(new MathToken(MathTokenType.DIVIDE, "/"), left, right);

        // Act
        double result = MathInterpreter.interpret(root);

        // Assert
        assertEquals(3.00, result);
    }

    @Test
    void testR() throws Exception {
        // Arrange
        AstNode div_left = new NumberNode(new MathToken(MathTokenType.NUMBER, "989"));
        AstNode div_right = new NumberNode(new MathToken(MathTokenType.NUMBER, "1000"));
        AstNode div = new DivisionNode(new MathToken(MathTokenType.DIVIDE, "/"), div_left, div_right);
        AstNode root = new RNode(new MathToken(MathTokenType.R, "r"), div);

        // Act
        double result = MathInterpreter.interpret(root);

        // Assert
        assertEquals(1.00, result);
    }

    @Test
    void testInvalidR() {
        // Arrange
        AstNode add_left = new NumberNode(new MathToken(MathTokenType.NUMBER, "0.95"));
        AstNode add_right = new NumberNode(new MathToken(MathTokenType.NUMBER, "1"));
        AstNode add = new AdditionNode(new MathToken(MathTokenType.MULTIPLY, "*"), add_left, add_right);
        AstNode root = new RNode(new MathToken(MathTokenType.R, "r"), add);

        // Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            MathInterpreter.interpret(root);
        });
    }
}