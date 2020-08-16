package com.ernsthaagsman.calculator.tokenizer;

import java.util.LinkedList;
import java.util.List;

public class Tokenizer {

    public static List<MathToken> tokenize(String input) throws InvalidTokenException{
        List<MathToken> tokens = new LinkedList<>();

        while(!input.isEmpty()){
            MathToken token = null;

            for (MathTokenType type : MathTokenType.values()){
                if(!type.isMatch(input))
                    continue;

                String match = type.match(input);
                token = new MathToken(type, match.strip());
                tokens.add(token);
                input = input.substring(match.length()).strip();
                break;
            }

            if (token == null) {
                String substring = input.substring(0, Math.min(200, input.length() - 1));
                throw new InvalidTokenException("Invalid token: " + substring);
            }

        }

        return tokens;
    }
}
