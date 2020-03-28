package qcha.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import qcha.TicTacToeModel;

import java.io.IOException;

public final class TicTacToeController {
    @FXML
    private GridPane board;
    @FXML
    private Label currentPlayerLabel;

    private TicTacToeModel model = new TicTacToeModel();

    // Menu Bar actions
    public void onNew(ActionEvent actionEvent) {
        board.getChildren().forEach(tile -> ((Rectangle) tile).setFill(Color.WHITE));
        model.reset();
    }

    public void onClose(ActionEvent actionEvent) {
        model.exit();
    }

    public void onAbout(ActionEvent actionEvent) {

    }

    public void onStatistic(ActionEvent actionEvent) {

    }

    public void handleMouseClicked(MouseEvent mouseEvent) throws IOException {
        if (!model.isGameOver()) {
            final Rectangle rectangle = (Rectangle) mouseEvent.getSource();
            if (rectangle.getFill().getClass() != ImagePattern.class) {
                final Image img;
                if (model.isPlayerXTurn()) {
                    img = new Image(getClass().getResource("/images/covid-19.png").openStream());
                } else {
                    img = new Image(getClass().getResource("/images/coronavirus.png").openStream());
                }

                rectangle.setFill(new ImagePattern(img));

                int row = board.getChildren().indexOf(rectangle) / 3;
                int column = board.getChildren().indexOf(rectangle) % 3;
                if (model.checkWinner(row, column)) {
                    model.setGameOver(true);
                    final Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Winner!");

                    // Header Text: null
                    alert.setHeaderText(null);
                    alert.setContentText("Player " + currentPlayerLabel.getText() + " win!");

                    alert.showAndWait();
                } else {
                    if (model.isPlayerXTurn()) {
                        currentPlayerLabel.setText("HUMANS");
                        model.setPlayerXTurn(false);
                    } else {
                        currentPlayerLabel.setText("COVID-19");
                        model.setPlayerXTurn(true);
                    }
                }
            }
        }
    }
}
