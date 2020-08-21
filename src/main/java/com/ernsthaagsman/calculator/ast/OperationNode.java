package com.ernsthaagsman.calculator.ast;

import com.ernsthaagsman.calculator.tokenizer.MathToken;

public abstract class OperationNode extends AstNode {
    private AstNode left;
    private AstNode right;

    public OperationNode(MathToken token, AstNode left, AstNode right){
        super(token);

        this.setLeft(left);
        this.setRight(right);
    }

    public AstNode getLeft() {
        return left;
    }

    public AstNode getRight() {
        return right;
    }

    public void setLeft(AstNode left) {
        this.left = left;
    }

    public void setRight(AstNode right) {
        this.right = right;
    }
}
