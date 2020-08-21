package com.ernsthaagsman.calculator.ast;

import com.ernsthaagsman.calculator.tokenizer.MathToken;

public class AdditionNode extends OperationNode {
    public AdditionNode(MathToken token, AstNode left, AstNode right){
        super(token, left, right);
    }
}
