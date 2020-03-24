package qcha.editor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public final class EditorModel {
    public void save(final TextFile textFile) {
        try {
            Files.write(textFile.getFile(), textFile.getContent(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public TextFile load(final Path file) {
        try {
            final List<String> lines = Files.readAllLines(file);
            return new TextFile(file, lines);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void close() {
        System.exit(0);
    }
}