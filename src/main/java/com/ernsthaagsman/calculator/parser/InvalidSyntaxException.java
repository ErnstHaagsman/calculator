package com.ernsthaagsman.calculator.parser;

public class InvalidSyntaxException extends Exception {
    public InvalidSyntaxException(){
        super();
    }

    public InvalidSyntaxException(String s){
        super(s);
    }
}
