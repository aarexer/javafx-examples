package qcha;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToeApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/tictactoe.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
