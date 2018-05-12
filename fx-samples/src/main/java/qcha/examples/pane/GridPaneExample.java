package qcha.examples.pane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GridPaneExample extends Application {
    private GridPane grid;

    @Override
    public void start(Stage primaryStage) throws Exception {
        initGridPane();

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initGridPane() {
        grid = new GridPane() {
            {
                setAlignment(Pos.CENTER);
                setHgap(10);
                setVgap(10);
                setPadding(new Insets(25, 25, 25, 25));
            }
        };

        Text title = new Text("Welcome") {
            {
                setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            }

        };
        grid.add(title, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField loginTextField = new TextField();
        grid.add(loginTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        Button signBtn = new Button("Sign in");
        HBox hbBtn = new HBox(10) {
            {
                setAlignment(Pos.BOTTOM_RIGHT);
                getChildren().add(signBtn);
            }
        };
        grid.add(hbBtn, 1, 4);

        final Text warning = new Text();
        grid.add(warning, 1, 6);

        signBtn.setOnAction(e -> {
            warning.setFill(Color.FIREBRICK);
            warning.setText("Sign in button pressed");
        });

    }
}
