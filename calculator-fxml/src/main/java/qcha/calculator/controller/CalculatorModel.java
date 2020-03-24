package qcha.calculator.controller;

public class CalculatorModel {

    public Number calculate(long number1, long number2, String operator) {
        switch (operator) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                if (number2 == 0) {
                    throw new ArithmeticException("Division by zero.");
                }

                return (double) number1 / number2;
        }

        throw new IllegalArgumentException("Unknown operator.");
    }
}