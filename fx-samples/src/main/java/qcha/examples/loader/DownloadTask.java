package qcha.examples.loader;

import javafx.concurrent.Task;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class DownloadTask extends Task<Void> {
    // buffer size used for reading and writing
    private static final int BUFFER_SIZE = 8192;

    private final String url;

    public DownloadTask(final String url) {
        this.url = url;
    }

    @Override
    protected Void call() throws Exception {
        final URLConnection connection = new URL(url).openConnection();
        final long length = connection.getContentLengthLong();

        try(final InputStream source = connection.getInputStream();
            final OutputStream sink = Files.newOutputStream(Paths.get("tmp"))) {

            long nread = 0L;
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = source.read(buf)) > 0) {
                sink.write(buf, 0, n);
                nread += n;

                updateProgress(nread, length);
            }
        }

        return null;
    }
}
