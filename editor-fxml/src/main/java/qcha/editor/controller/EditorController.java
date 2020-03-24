package qcha.editor.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import qcha.editor.EditorModel;
import qcha.editor.TextFile;

import java.io.File;
import java.util.Arrays;

public final class EditorController {
    @FXML
    private TextArea areaText;

    private TextFile currentTextFile;
    private final EditorModel model = new EditorModel();

    @FXML
    private void onNew(final ActionEvent event) {
        currentTextFile = null;
        areaText.clear();
        setWindowTitle("Unknown");
    }

    @FXML
    private void onSave(final ActionEvent event) {
        if (currentTextFile != null) {
            final TextFile textFile = new TextFile(currentTextFile.getFile(), Arrays.asList(areaText.getText().split(System.lineSeparator())));
            model.save(textFile);
        } else {
            final FileChooser fileChooser = createTextFileChooser();

            File file = fileChooser.showSaveDialog(areaText.getScene().getWindow());
            if (file != null) {
                setWindowTitle(file.getName());
                final TextFile textFile = new TextFile(file.toPath(), Arrays.asList(areaText.getText().split(System.lineSeparator())));
                model.save(textFile);
            }
        }
    }

    @FXML
    private void onLoad(final ActionEvent event) {
        final FileChooser fileChooser = createTextFileChooser();

        final File file = fileChooser.showOpenDialog(areaText.getScene().getWindow());
        if (file != null) {
            currentTextFile = model.load(file.toPath());
            setWindowTitle(file.getName());
            areaText.clear();

            currentTextFile.getContent().forEach(line -> areaText.appendText(line + System.lineSeparator()));
        }
    }

    @FXML
    private void onClose(final ActionEvent event) {
        model.close();
    }

    @FXML
    private void onAbout(final ActionEvent event) {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setContentText("MVC editor application.");
        alert.show();
    }

    private FileChooser createTextFileChooser() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text documents", "*.txt"),
                new FileChooser.ExtensionFilter("All files", "*.*"));

        return fileChooser;
    }

    private void setWindowTitle(String fileName) {
        ((Stage) areaText.getScene().getWindow()).setTitle(fileName + "- Editor");
    }
}
