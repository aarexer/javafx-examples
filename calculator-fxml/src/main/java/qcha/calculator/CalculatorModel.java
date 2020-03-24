package qcha.calculator;

public final class CalculatorModel {

    public final Number calculate(final long number1, final long number2, final String operator) {
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