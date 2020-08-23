package com.ernsthaagsman.calculator.ast;

import com.ernsthaagsman.calculator.tokenizer.MathToken;

public class RNode extends AstNode {
    private AstNode child;

    public RNode(MathToken token, AstNode child){
        super(token);

        setChild(child);
    }

    public AstNode getChild() {
        return child;
    }

    public void setChild(AstNode child){
        this.child = child;
    }
}
