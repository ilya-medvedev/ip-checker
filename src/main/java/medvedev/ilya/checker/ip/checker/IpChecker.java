package medvedev.ilya.checker.ip.checker;

import medvedev.ilya.checker.ip.service.EmailNotificationService;
import medvedev.ilya.checker.ip.service.IpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IpChecker implements Closeable {
    private static final Logger LOGGER = LoggerFactory.getLogger(IpChecker.class);

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private final IpService ipService;
    private final EmailNotificationService notificationService;
    private final int timeout;

    private String ip = null;

    public IpChecker(final IpService ipService, final EmailNotificationService notificationService, final int timeout) {
        this.ipService = ipService;
        this.notificationService = notificationService;
        this.timeout = timeout;
    }

    private void checkIp() {
        final String newIp = ipService.currentIp();

        if (ip != null) {
            if (ip.equals(newIp)) {
                return;
            } else {
                notificationService.sendNotification("IP address has been changed: " + newIp);
            }
        } else {
            notificationService.sendNotification("Server is running: " + newIp);
        }

        ip = newIp;
    }

    private void exceptionHandler() {
        try {
            checkIp();
        } catch(final Exception e) {
            final String message = e.getMessage();

            LOGGER.warn(message, e);
        }
    }

    public void start() {
        executorService.scheduleWithFixedDelay(this::exceptionHandler, 0, timeout, TimeUnit.MINUTES);
    }

    @Override
    public void close() throws IOException {
        executorService.shutdown();
    }
}
