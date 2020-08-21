package com.ernsthaagsman.calculator.ast;

import com.ernsthaagsman.calculator.tokenizer.MathToken;

public class DivisionNode extends OperationNode {
    public DivisionNode(MathToken token, AstNode left, AstNode right){
        super(token, left, right);
    }
}
