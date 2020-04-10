package qcha.examples.chart;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class LineChartView extends BorderPane {
    private Button openChartBtn;
    private Label infoLbl;
    private LineChart<Number, Number> lineChart;

    private LineChartController controller = new LineChartController();

    public LineChartView(Stage stage) {
        openChartBtn = new Button("Open");
        openChartBtn.setMinSize(150, 50);
        openChartBtn.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(stage);

            if (file != null) {
                try {
                    XYChart.Series<Number, Number> series = new XYChart.Series<>();
                    ObservableList<XYChart.Data<Number, Number>> data = controller.receivePoints(file);
                    series.getData().addAll(data);

                    lineChart.getData().addAll(series);
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Error data alert");
                    alert.setHeaderText("Error parsing data");
                    alert.setContentText(ex.getMessage());

                    alert.showAndWait();
                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Error alert");
                    alert.setHeaderText("Can not add user");
                    alert.setContentText(ex.getMessage());

                    alert.showAndWait();
                }
            }
        });

        infoLbl = new Label("Choose file");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        lineChart = new LineChart<>(xAxis, yAxis);

        setCenter(lineChart);

        initControlPanel();
    }

    private void initControlPanel() {
        HBox controlPanel = new HBox();
        controlPanel.setSpacing(10);
        infoLbl.setPadding(new Insets(15, 0, 15, 0));
        infoLbl.setFont(new Font(15));
        controlPanel.getChildren().addAll(openChartBtn, infoLbl);

        setBottom(controlPanel);
    }
}
