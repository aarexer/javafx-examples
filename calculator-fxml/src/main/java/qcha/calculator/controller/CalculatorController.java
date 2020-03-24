package qcha.calculator.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import qcha.calculator.CalculatorModel;

public final class CalculatorController {
    @FXML
    private Text hint;

    @FXML
    private Text output;

    private long leftOperand = 0L;
    private String operator = "";
    private boolean isNewCalculation = false;

    private final CalculatorModel calculatorModel = new CalculatorModel();

    @FXML
    private void processNumpad(final ActionEvent event) {
        // clear output if it's new calculation
        if (isNewCalculation) {
            output.setText("");
            isNewCalculation = false;
        }

        final String number = ((Button) event.getSource()).getText();
        output.setText(output.getText() + number);
    }

    @FXML
    private void processOperator(final ActionEvent event) {
        final String value = ((Button) event.getSource()).getText();

        if (!"=".equals(value)) {
            if (value.isEmpty()) {
                return;
            }

            operator = value;

            // repeated sign pressing
            if (output.getText().isEmpty()) {
                return;
            }

            leftOperand = Long.parseLong(output.getText());
            hint.setText(output.getText() + " " + operator);
            output.setText("");
        } else {
            // calculation if '=' pressed
            if (operator.isEmpty() || output.getText().isEmpty()) {
                return;
            }

            try {
                output.setText(String.valueOf(calculatorModel.calculate(leftOperand, Long.parseLong(output.getText()), operator)));
            } catch (ArithmeticException ex) {
                output.setText("Zero division error.");
            } catch (IllegalArgumentException ex) {
                output.setText("Unknown operator.");
            }

            operator = "";
            hint.setText("");
            isNewCalculation = true;
        }
    }
}
