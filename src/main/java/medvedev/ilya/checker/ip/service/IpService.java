package medvedev.ilya.checker.ip.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.net.URL;

public class IpService {
    private final URL url;

    public IpService(final URL url) {
        this.url = url;
    }

    public String currentIp() {
        try (
                final InputStream inputStream = url.openConnection()
                        .getInputStream();
                final Reader reader = new InputStreamReader(inputStream);
                final BufferedReader in = new BufferedReader(reader)
        ) {
            return in.readLine();
        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
