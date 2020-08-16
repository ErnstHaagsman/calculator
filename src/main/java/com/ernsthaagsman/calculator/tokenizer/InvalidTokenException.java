package com.ernsthaagsman.calculator.tokenizer;

public class InvalidTokenException extends Exception {
    public InvalidTokenException(){
        super();
    }

    public InvalidTokenException(String s){
        super(s);
    }
}
