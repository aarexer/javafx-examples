package qcha.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("form_login.fxml"));
        Scene scene = new Scene(root, 640, 480);

        stage.setTitle("FXML Login form");
        stage.setResizable(false);
        stage.setScene(scene);

        stage.show();
    }
}
