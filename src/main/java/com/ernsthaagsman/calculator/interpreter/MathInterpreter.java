package com.ernsthaagsman.calculator.interpreter;

import com.ernsthaagsman.calculator.ast.*;
import com.ernsthaagsman.calculator.parser.InvalidSyntaxException;

public class MathInterpreter {
    public static double interpret(AstNode tree){
        return visit(tree);
    }

    private static double visit(AstNode node){
        if(node instanceof NumberNode){
            return visitNumber((NumberNode)node);
        } else if (node instanceof AdditionNode){
            return visitAddition((AdditionNode)node);
        } else if (node instanceof SubtractionNode){
            return visitSubtraction((SubtractionNode)node);
        } else if (node instanceof MultiplicationNode){
            return visitMultiplication((MultiplicationNode)node);
        } else if (node instanceof DivisionNode){
            return visitDivision((DivisionNode)node);
        }

        throw new UnsupportedOperationException("Unknown node type: " + node.getClass().getName());
    }

    private static double visitNumber(NumberNode node){
        return Double.parseDouble(node.getToken().getToken());
    }

    private static double visitAddition(AdditionNode node){
        return visit(node.getLeft()) + visit(node.getRight());
    }

    private static double visitSubtraction(SubtractionNode node) {
        return visit(node.getLeft()) - visit(node.getRight());
    }

    private static double visitMultiplication(MultiplicationNode node) {
        return visit(node.getLeft()) * visit(node.getRight());
    }

    private static double visitDivision(DivisionNode node) {
        return visit(node.getLeft()) / visit(node.getRight());
    }
}
