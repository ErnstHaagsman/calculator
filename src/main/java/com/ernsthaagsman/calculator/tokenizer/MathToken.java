package com.ernsthaagsman.calculator.tokenizer;

public class MathToken {
    private final MathTokenType type;
    private final String token;

    public MathToken(MathTokenType type, String token){
        this.type = type;
        this.token = token;
    }

    public MathTokenType getType() {
        return type;
    }

    public String getToken() {
        return token;
    }
}
