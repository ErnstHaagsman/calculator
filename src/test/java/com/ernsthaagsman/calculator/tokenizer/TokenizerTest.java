package com.ernsthaagsman.calculator.tokenizer;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {
    @Test
    void testInteger() throws Exception{
        // Act
        List<MathToken> tokens = Tokenizer.tokenize("1");

        // Assert
        assertEquals(1, tokens.size());
        assertEquals(MathTokenType.NUMBER, tokens.get(0).getType());
    }

    @Test
    void testPlus() throws Exception{
        // Act
        List<MathToken> tokens = Tokenizer.tokenize("+");

        // Assert
        assertEquals(1, tokens.size());
        assertEquals(MathTokenType.PLUS, tokens.get(0).getType());
    }

    @Test
    void testSimpleExpression() throws Exception{
        // Act
        List<MathToken> tokens = Tokenizer.tokenize("1 + 1");

        // Assert
        assertEquals(3, tokens.size());
        assertEquals(MathTokenType.NUMBER, tokens.get(0).getType());
        assertEquals(MathTokenType.PLUS, tokens.get(1).getType());
        assertEquals(MathTokenType.NUMBER, tokens.get(2).getType());
    }
}