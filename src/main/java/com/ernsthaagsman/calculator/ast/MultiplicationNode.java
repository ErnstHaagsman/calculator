package com.ernsthaagsman.calculator.ast;

import com.ernsthaagsman.calculator.tokenizer.MathToken;

public class MultiplicationNode extends OperationNode {
    public MultiplicationNode(MathToken token, AstNode left, AstNode right){
        super(token, left, right);
    }
}
