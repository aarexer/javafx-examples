package qcha.examples.progressbar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DownloaderApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(new DownloaderView()));
        primaryStage.setTitle("Downloader");
        primaryStage.show();
    }
}
