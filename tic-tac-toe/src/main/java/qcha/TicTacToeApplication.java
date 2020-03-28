package qcha;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import qcha.view.TicTacToeView;

import java.io.IOException;

public class TicTacToeApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        TicTacToeView view = new TicTacToeView();

        primaryStage.setScene(new Scene(view, 593, 650));
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
