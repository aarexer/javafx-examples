package qcha.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class FormLoginController {
    @FXML private Text warning;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        warning.setText("Sign in button pressed");
    }
}
