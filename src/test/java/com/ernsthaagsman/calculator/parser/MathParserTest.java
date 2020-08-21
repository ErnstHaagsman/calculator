package com.ernsthaagsman.calculator.parser;

import com.ernsthaagsman.calculator.ast.*;
import com.ernsthaagsman.calculator.tokenizer.MathToken;
import com.ernsthaagsman.calculator.tokenizer.MathTokenType;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class MathParserTest {
    @Test
    void testNumber() throws Exception{
        // Arrange
        List<MathToken> tokenList = new LinkedList<>(List.of(
                new MathToken(MathTokenType.NUMBER, "1")
        ));

        // Act
        MathParser parser = new MathParser();
        AstNode tree = parser.parse(tokenList);

        // Assert
        assertThat(tree, instanceOf(NumberNode.class));
        assertEquals(tree.getToken().getToken(), "1");
    }

    @Test
    void testAddition() throws Exception{
        // Arrange
        List<MathToken> tokenList = new LinkedList<>(List.of(
                new MathToken(MathTokenType.NUMBER, "1"),
                new MathToken(MathTokenType.PLUS, "+"),
                new MathToken(MathTokenType.NUMBER, "1")
        ));

        // Act
        MathParser parser = new MathParser();
        AstNode tree = parser.parse(tokenList);

        // Assert
        assertThat(tree, instanceOf(AdditionNode.class));
        AdditionNode node = (AdditionNode)tree;
        assertThat(node.getLeft(), instanceOf(NumberNode.class));
        assertThat(node.getRight(), instanceOf(NumberNode.class));
    }

    @Test
    void testSubtraction() throws Exception{
        // Arrange
        List<MathToken> tokenList = new LinkedList<>(List.of(
                new MathToken(MathTokenType.NUMBER, "1"),
                new MathToken(MathTokenType.MINUS, "-"),
                new MathToken(MathTokenType.NUMBER, "1")
        ));

        // Act
        MathParser parser = new MathParser();
        AstNode tree = parser.parse(tokenList);

        // Assert
        assertThat(tree, instanceOf(SubtractionNode.class));
        SubtractionNode node = (SubtractionNode) tree;
        assertThat(node.getLeft(), instanceOf(NumberNode.class));
        assertThat(node.getRight(), instanceOf(NumberNode.class));
    }

    @Test
    void testMultiplication() throws Exception{
        // Arrange
        List<MathToken> tokenList = new LinkedList<>(List.of(
                new MathToken(MathTokenType.NUMBER, "1"),
                new MathToken(MathTokenType.MULTIPLY, "*"),
                new MathToken(MathTokenType.NUMBER, "1")
        ));

        // Act
        MathParser parser = new MathParser();
        AstNode tree = parser.parse(tokenList);

        // Assert
        assertThat(tree, instanceOf(MultiplicationNode.class));
        OperationNode node = (OperationNode) tree;
        assertThat(node.getLeft(), instanceOf(NumberNode.class));
        assertThat(node.getRight(), instanceOf(NumberNode.class));
    }

    @Test
    void testDivision() throws Exception{
        // Arrange
        List<MathToken> tokenList = new LinkedList<>(List.of(
                new MathToken(MathTokenType.NUMBER, "1"),
                new MathToken(MathTokenType.DIVIDE, "/"),
                new MathToken(MathTokenType.NUMBER, "1")
        ));

        // Act
        MathParser parser = new MathParser();
        AstNode tree = parser.parse(tokenList);

        // Assert
        assertThat(tree, instanceOf(DivisionNode.class));
        OperationNode node = (OperationNode) tree;
        assertThat(node.getLeft(), instanceOf(NumberNode.class));
        assertThat(node.getRight(), instanceOf(NumberNode.class));
    }

    @Test
    void testParens() throws Exception{
        // Arrange
        List<MathToken> tokenList = new LinkedList<>(List.of(
                new MathToken(MathTokenType.OPAREN, "("),
                new MathToken(MathTokenType.NUMBER, "1"),
                new MathToken(MathTokenType.CPAREN, ")")
        ));

        // Act
        MathParser parser = new MathParser();
        AstNode tree = parser.parse(tokenList);

        // Assert
        assertThat(tree, instanceOf(NumberNode.class));
    }

    @Test
    void testNested() throws Exception{
        // Arrange
        List<MathToken> tokenList = new LinkedList<>(List.of(
                new MathToken(MathTokenType.OPAREN, "("),
                new MathToken(MathTokenType.NUMBER, "2"),
                new MathToken(MathTokenType.PLUS, "+"),
                new MathToken(MathTokenType.NUMBER, "2"),
                new MathToken(MathTokenType.CPAREN, ")"),
                new MathToken(MathTokenType.MULTIPLY, "*"),
                new MathToken(MathTokenType.NUMBER, "5")
        ));

        // Act
        MathParser parser = new MathParser();
        AstNode tree = parser.parse(tokenList);

        // Assert
        assertThat(tree, instanceOf(MultiplicationNode.class));
        OperationNode node = (OperationNode) tree;
        assertThat(node.getLeft(), instanceOf(AdditionNode.class));
        assertThat(node.getRight(), instanceOf(NumberNode.class));
        OperationNode addition = (OperationNode)node.getLeft();
        assertThat(addition.getLeft(), instanceOf(NumberNode.class));
        assertThat(addition.getRight(), instanceOf(NumberNode.class));
    }

    @Test
    void testNoRhs(){
        // Arrange
        List<MathToken> tokenList = new LinkedList<>(List.of(
                new MathToken(MathTokenType.NUMBER, "1"),
                new MathToken(MathTokenType.PLUS, "+")
        ));

        // Act
        MathParser parser = new MathParser();
        assertThrows(InvalidSyntaxException.class, () -> {
            parser.parse(tokenList);
        });
    }

    @Test
    void testNoNumbers(){
        // Arrange
        List<MathToken> tokenList = new LinkedList<>(List.of(
                new MathToken(MathTokenType.PLUS, "+")
        ));

        // Act
        MathParser parser = new MathParser();
        assertThrows(InvalidSyntaxException.class, () -> {
            parser.parse(tokenList);
        });
    }
}