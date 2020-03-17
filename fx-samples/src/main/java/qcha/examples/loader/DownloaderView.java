package qcha.examples.loader;

import javafx.concurrent.Task;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public final class DownloaderView extends VBox {
    private final TextField urlTf;

    public DownloaderView() {
        setPrefSize(400, 600);
        urlTf = new TextField();
        urlTf.setOnAction(e -> startDownload());

        final Button downloadBtn = new Button("Download");
        downloadBtn.setOnAction(e -> startDownload());

        HBox downloadPanel = new HBox(urlTf, downloadBtn);
        HBox.setHgrow(urlTf, Priority.ALWAYS);

        getChildren().addAll(downloadPanel);
    }

    private void startDownload() {
        final Task<Void> task = new DownloadTask(urlTf.getText());

        final StackPane sp = new StackPane();

        final ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefHeight(50);
        progressBar.setMaxWidth(Double.MAX_VALUE);
        progressBar.progressProperty().bind(task.progressProperty());

        sp.getChildren().addAll(progressBar, new Label(urlTf.getText()));
        getChildren().add(sp);

        urlTf.clear();

        task.setOnFailed(event -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(event.getSource().getException().toString());
            alert.showAndWait();
            task.cancel();
            getChildren().remove(progressBar);
        });

        final Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
