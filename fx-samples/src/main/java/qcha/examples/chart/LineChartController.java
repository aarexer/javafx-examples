package qcha.examples.chart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LineChartController {
    private LineChartModel model = new LineChartModel();

    public ObservableList<XYChart.Data<Number, Number>> receivePoints(File file) throws IOException {
        List<Pair<? extends Number, ? extends Number>> pairs = model.readData(file);
        return FXCollections.observableArrayList(pairs.stream().map(pair ->
                new XYChart.Data<Number, Number>(pair.getKey(), pair.getValue())).collect(Collectors.toList())
        );
    }
}
