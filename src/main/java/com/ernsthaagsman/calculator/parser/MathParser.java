package com.ernsthaagsman.calculator.parser;

import com.ernsthaagsman.calculator.ast.*;
import com.ernsthaagsman.calculator.tokenizer.MathToken;
import com.ernsthaagsman.calculator.tokenizer.MathTokenType;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class MathParser {
    private Queue<MathToken> tokens;

    public MathParser() {

    }

    public AstNode parse(Collection<MathToken> tokens) throws InvalidSyntaxException{
        this.tokens = new LinkedList<MathToken>(tokens);

        return parseExpression();
    }

    private MathToken consume(MathTokenType type) throws InvalidSyntaxException{
        if (tokens.peek().getType() == type){
            return tokens.remove();
        } else {
            throw new InvalidSyntaxException("Expected: " + type + ", got: " + tokens.peek().getType());
        }
    }

    private AstNode parseExpression() throws InvalidSyntaxException{
        AstNode node = parseTerm();

        while(!tokens.isEmpty() &&
                (tokens.peek().getType() == MathTokenType.PLUS ||
                        tokens.peek().getType() == MathTokenType.MINUS)) {
            MathToken token = tokens.peek();
            switch (token.getType()) {
                case PLUS:
                    token = consume(MathTokenType.PLUS);
                    node = new AdditionNode(token, node, parseTerm());
                    break;
                case MINUS:
                    token = consume(MathTokenType.MINUS);
                    node = new SubtractionNode(token, node, parseTerm());
                    break;
            }
        }

        return node;
    }

    private AstNode parseTerm() throws InvalidSyntaxException{
        AstNode node = parseFactor();

        while(!tokens.isEmpty() &&
                (tokens.peek().getType() == MathTokenType.MULTIPLY ||
                        tokens.peek().getType() == MathTokenType.DIVIDE)) {
            MathToken token = tokens.peek();
            switch(token.getType()){
                case MULTIPLY:
                    token = consume(MathTokenType.MULTIPLY);
                    node = new MultiplicationNode(token, node, parseTerm());
                    break;
                case DIVIDE:
                    token = consume(MathTokenType.DIVIDE);
                    node = new DivisionNode(token, node, parseTerm());
                    break;
            }
        }

        return node;
    }

    private AstNode parseFactor() throws InvalidSyntaxException{
        if (tokens.isEmpty()){
            throw new InvalidSyntaxException("Number expected");
        }

        if(tokens.peek().getType() == MathTokenType.NUMBER) {
            MathToken token = consume(MathTokenType.NUMBER);
            return new NumberNode(token);
        } else if (tokens.peek().getType() == MathTokenType.OPAREN) {
            consume(MathTokenType.OPAREN);
            AstNode node = parseExpression();
            consume(MathTokenType.CPAREN);
            return node;
        } else {
            throw new InvalidSyntaxException("Unexpected: " + tokens.peek().getType());
        }
    }
}
