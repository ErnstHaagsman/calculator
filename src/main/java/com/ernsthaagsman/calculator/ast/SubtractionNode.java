package com.ernsthaagsman.calculator.ast;

import com.ernsthaagsman.calculator.tokenizer.MathToken;

public class SubtractionNode extends OperationNode {
    public SubtractionNode(MathToken token, AstNode left, AstNode right){
        super(token, left, right);
    }
}
