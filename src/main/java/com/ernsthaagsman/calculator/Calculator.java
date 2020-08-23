package com.ernsthaagsman.calculator;

import com.ernsthaagsman.calculator.ast.AstNode;
import com.ernsthaagsman.calculator.interpreter.MathInterpreter;
import com.ernsthaagsman.calculator.parser.InvalidSyntaxException;
import com.ernsthaagsman.calculator.parser.MathParser;
import com.ernsthaagsman.calculator.tokenizer.InvalidTokenException;
import com.ernsthaagsman.calculator.tokenizer.MathToken;
import com.ernsthaagsman.calculator.tokenizer.Tokenizer;

import java.util.List;

public class Calculator {
    String calculateExpression(String expression){
        try {
            List<MathToken> tokens = Tokenizer.tokenize(expression);
            MathParser parser = new MathParser();
            AstNode root = parser.parse(tokens);
            double result = MathInterpreter.interpret(root);

            return Double.toString(result);
        }
        catch (InvalidSyntaxException| InvalidTokenException e){
            return e.getMessage();
        }
    }
}
