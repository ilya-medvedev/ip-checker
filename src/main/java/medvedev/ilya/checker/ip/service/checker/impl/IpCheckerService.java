package medvedev.ilya.checker.ip.service.checker.impl;

import medvedev.ilya.checker.ip.service.checker.CheckerService;
import medvedev.ilya.checker.ip.service.notification.NotificationService;
import medvedev.ilya.checker.ip.service.notification.NotificationServiceException;
import medvedev.ilya.checker.ip.service.ip.IpService;
import medvedev.ilya.checker.ip.service.ip.IpServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IpCheckerService implements CheckerService, Closeable {
    private static final Logger LOGGER = LoggerFactory.getLogger(IpCheckerService.class);

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private final IpService ipService;
    private final NotificationService notificationService;
    private final int timeout;

    private String ip = null;

    public IpCheckerService(
            final IpService ipService,
            final NotificationService notificationService,
            final int timeout
    ) {
        this.ipService = ipService;
        this.notificationService = notificationService;
        this.timeout = timeout;
    }

    private void checkIp() {
        final String newIp = ipService.currentIp();

        if (ip != null) {
            if (!ip.equals(newIp)) {
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
        } catch (final IpServiceException | NotificationServiceException e) {
            final String message = e.getMessage();

            LOGGER.warn(message, e);
        } catch (final Exception e) {
            LOGGER.error("Unknown error.", e);

            executorService.shutdownNow();
        }
    }

    @Override
    public void run() {
        executorService.scheduleWithFixedDelay(this::exceptionHandler, 0, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void close() throws IOException {
        executorService.shutdown();
    }
}
