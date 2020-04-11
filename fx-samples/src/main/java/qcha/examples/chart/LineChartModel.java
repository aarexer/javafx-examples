package qcha.examples.chart;

import javafx.util.Pair;
import qcha.examples.chart.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LineChartModel {
    public List<Pair<? extends Number, ? extends Number>> readData(File file) throws IOException {
        final List<String> lines = FileUtils.readAll(file);
        return lines.stream().map(line -> {
            String[] splitted = line.trim().split(",");
            return new Pair<>(Integer.valueOf(splitted[0].trim()), Integer.valueOf(splitted[1].trim()));
        }).collect(Collectors.toList());
    }
}
