package com.ernsthaagsman.calculator;

import com.ernsthaagsman.calculator.tokenizer.MathToken;
import com.ernsthaagsman.calculator.tokenizer.Tokenizer;

import java.util.List;

public class CalculatorCli {
    public static void main(String[] args) throws Exception {
        String expr = "1 + 1";

        List<MathToken> tokenList = Tokenizer.tokenize(expr);

        for (MathToken t : tokenList){
            System.out.println(t.getType());
        }
    }
}
