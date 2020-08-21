package com.ernsthaagsman.calculator.ast;

import com.ernsthaagsman.calculator.tokenizer.MathToken;

public class NumberNode extends AstNode {
    public NumberNode(MathToken token) {
        super(token);
    }
}
