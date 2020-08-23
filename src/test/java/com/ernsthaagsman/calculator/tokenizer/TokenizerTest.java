package com.ernsthaagsman.calculator.tokenizer;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {
    @Test
    void testInteger() throws Exception {
        // Act
        List<MathToken> tokens = Tokenizer.tokenize("1");

        // Assert
        assertEquals(1, tokens.size());
        assertEquals(MathTokenType.NUMBER, tokens.get(0).getType());
    }

    @Test
    void testFloat() throws Exception {
        // Act
        List<MathToken> tokens = Tokenizer.tokenize("1.5");

        // Assert
        assertEquals(1, tokens.size());
        assertEquals(MathTokenType.NUMBER, tokens.get(0).getType());
    }

    @Test
    void testPlus() throws Exception {
        // Act
        List<MathToken> tokens = Tokenizer.tokenize("+");

        // Assert
        assertEquals(1, tokens.size());
        assertEquals(MathTokenType.PLUS, tokens.get(0).getType());
    }

    @Test
    void testMinus() throws Exception {
        // Act
        List<MathToken> tokens = Tokenizer.tokenize("-");

        // Assert
        assertEquals(1, tokens.size());
        assertEquals(MathTokenType.MINUS, tokens.get(0).getType());
    }

    @Test
    void testSimpleExpression() throws Exception {
        // Act
        List<MathToken> tokens = Tokenizer.tokenize("1 + 1");

        // Assert
        assertEquals(3, tokens.size());
        assertEquals(MathTokenType.NUMBER, tokens.get(0).getType());
        assertEquals(MathTokenType.PLUS, tokens.get(1).getType());
        assertEquals(MathTokenType.NUMBER, tokens.get(2).getType());
    }

    @Test
    void testMultiplication() throws Exception {
        // Act
        List<MathToken> tokens = Tokenizer.tokenize("2 * 5");

        // Assert
        assertEquals(3, tokens.size());
        assertEquals(MathTokenType.NUMBER, tokens.get(0).getType());
        assertEquals(MathTokenType.MULTIPLY, tokens.get(1).getType());
        assertEquals(MathTokenType.NUMBER, tokens.get(2).getType());
    }


    @Test
    void testDivision() throws Exception {
        // Act
        List<MathToken> tokens = Tokenizer.tokenize("15 / 3");

        // Assert
        assertEquals(3, tokens.size());
        assertEquals(MathTokenType.NUMBER, tokens.get(0).getType());
        assertEquals(MathTokenType.DIVIDE, tokens.get(1).getType());
        assertEquals(MathTokenType.NUMBER, tokens.get(2).getType());
    }


    @Test
    void testParens() throws Exception {
        // Act
        List<MathToken> tokens = Tokenizer.tokenize("15 / (1 + 2)");

        // Assert
        assertEquals(7, tokens.size());
        assertEquals(MathTokenType.OPAREN, tokens.get(2).getType());
        assertEquals(MathTokenType.CPAREN, tokens.get(6).getType());
    }

    @Test
    void testR() throws Exception {
        // Act
        List<MathToken> tokens = Tokenizer.tokenize("r(0.75)");

        // Assert
        assertEquals(4, tokens.size());
        assertEquals(MathTokenType.R, tokens.get(0).getType());
    }

    @Test
    void testInvalidToken() {
        assertThrows(InvalidTokenException.class, () -> {
            Tokenizer.tokenize("This is not a valid mathematical expression");
        });
    }
}