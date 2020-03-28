package qcha.view;

import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import static qcha.Constants.TILES_IN_COLUMN;
import static qcha.Constants.TILES_IN_ROW;

public final class TicTacToeView extends BorderPane {
    private MenuBar menuBar;
    private GridPane gameBoard;
    private Label currentPlayerLabel;

    private TicTacToeViewModel viewModel = new TicTacToeViewModel();

    public TicTacToeView() {
        initMenuBar();
        setTop(menuBar);

        setCenter(createGameBoard());

        createInfoPanel();
        setBottom(createInfoPanel());
    }

    private HBox createInfoPanel() {
        HBox hBox = new HBox();
        hBox.setSpacing(5d);

        Label info = new Label("Player Turn: ");
        info.setFont(new Font(21d));
        currentPlayerLabel = new Label("X");
        currentPlayerLabel.setFont(new Font(21d));

        hBox.getChildren().addAll(info, currentPlayerLabel);

        return hBox;
    }

    private GridPane createGameBoard() {
        gameBoard = new GridPane();
        for (int i = 0; i < TILES_IN_COLUMN; i++) {
            for (int j = 0; j < TILES_IN_ROW; j++) {
                final Tile tile = new Tile();
                gameBoard.add(tile, i, j);

                tile.setOnMouseClicked(e -> {
                    if (!viewModel.isGameOver()) {
                        if (e.getButton() == MouseButton.PRIMARY && tile.nonMarked()) {
                            if (viewModel.isXPlayerTurn()) {
                                tile.markAsX();
                            } else {
                                tile.markAs0();
                            }

                            if (viewModel.checkWinner(modelForCheck())) {
                                viewModel.setGameOver(true);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Winner!");

                                // Header Text: null
                                alert.setHeaderText(null);
                                alert.setContentText("Player " + currentPlayerLabel.getText() + " win!");

                                alert.showAndWait();
                            } else {
                                if (viewModel.isXPlayerTurn()) {
                                    viewModel.setXPlayerTurn(false);
                                    currentPlayerLabel.setText("O");
                                } else {
                                    viewModel.setXPlayerTurn(true);
                                    currentPlayerLabel.setText("X");
                                }
                                tile.setMarked();
                            }
                        }
                    }
                });
            }
        }

        return gameBoard;
    }

    private String[][] modelForCheck() {
        String[][] model = new String[TILES_IN_COLUMN][TILES_IN_ROW];
        for (int i = 0; i < TILES_IN_COLUMN; i++) {
            for (int j = 0; j < TILES_IN_ROW; j++) {
                model[i][j] = ((Tile) gameBoard.getChildren().get(j + 3 * i)).getValue();
            }
        }

        return model;
    }

    private void initMenuBar() {
        menuBar = new MenuBar();

        // Game menu
        Menu menu = new Menu("Game");

        MenuItem newGame = new MenuItem("New");
        newGame.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        newGame.setOnAction(e -> {
            setCenter(createGameBoard());

            createInfoPanel();
            setBottom(createInfoPanel());

            viewModel = new TicTacToeViewModel();
        });

        MenuItem statistic = new MenuItem("Statistic");
        statistic.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
        statistic.setOnAction(e -> {

        });

        MenuItem exit = new MenuItem("Exit");
        exit.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        exit.setOnAction(e -> {
            viewModel.exit();
        });

        menu.getItems().addAll(newGame, statistic, exit);

        menuBar.getMenus().add(menu);
    }
}
