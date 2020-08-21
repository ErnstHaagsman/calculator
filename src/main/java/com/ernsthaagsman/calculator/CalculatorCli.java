package com.ernsthaagsman.calculator;

import java.util.Scanner;

public class CalculatorCli {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("Use 'exit' to end");

        while(true){
            System.out.print("calc> ");
            String expression = scanner.nextLine();

            if(expression.isBlank())
                continue;

            if(expression.trim().toLowerCase().equals("exit"))
                return;

            System.out.println(calculator.calculateExpression(expression));
        }
    }
}
