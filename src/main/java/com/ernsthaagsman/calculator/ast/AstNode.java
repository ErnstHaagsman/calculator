package com.ernsthaagsman.calculator.ast;

import com.ernsthaagsman.calculator.tokenizer.MathToken;

public abstract class AstNode {
    private MathToken token;

    public AstNode(MathToken token){
        this.token = token;
    }

    public MathToken getToken() {
        return token;
    }
}
