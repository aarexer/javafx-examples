package qcha.view;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    private static final double TILE_SIZE = 200;

    private Text text;
    private boolean isMarked;

    public Tile() {
        Rectangle border = new Rectangle(TILE_SIZE, TILE_SIZE);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        text = new Text();
        text.setFont(Font.font(72));

        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);
    }

    public void markAsX() {
        text.setText("X");
    }

    public void markAs0() {
        text.setText("0");
    }

    public String getValue() {
        return text.getText();
    }

    public double getTileCentralX() {
        return getTranslateX() + TILE_SIZE / 2;
    }

    public double getTileCentralY() {
        return getTranslateY() + TILE_SIZE / 2;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public boolean nonMarked() {
        return !isMarked();
    }

    public void setMarked() {
        isMarked = true;
    }
}