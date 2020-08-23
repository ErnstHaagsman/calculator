package com.ernsthaagsman.calculator.tokenizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MathTokenType {
    PLUS ("\\+"),
    MINUS("\\-"),
    MULTIPLY("\\*"),
    DIVIDE("/"),
    OPAREN("\\("),
    CPAREN("\\)"),
    R("r"),
    NUMBER ("[0-9.]+");

    private final Pattern regex;
    MathTokenType(String regex){
        this.regex = Pattern.compile("\\A(" + regex + ")");
    }

    public boolean isMatch(String input){
        return regex.matcher(input).lookingAt();
    }

    public String match(String input){
        Matcher m = regex.matcher(input);
        m.lookingAt();
        return m.group();
    }
}
