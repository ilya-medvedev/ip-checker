package medvedev.ilya.checker.ip.service.checker.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import medvedev.ilya.checker.ip.service.checker.CheckerService;
import medvedev.ilya.checker.ip.service.ip.IpService;
import medvedev.ilya.checker.ip.service.ip.IpServiceException;
import medvedev.ilya.checker.ip.service.notification.NotificationService;
import medvedev.ilya.checker.ip.service.notification.NotificationServiceException;

import java.io.Closeable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
public class IpCheckerService implements CheckerService, Closeable {
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private final IpService ipService;
    private final NotificationService notificationService;
    private final int timeout;

    private String ip = null;

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

            log.warn(message, e);
        } catch (final Exception e) {
            log.error("Unknown error.", e);

            executorService.shutdownNow();
        }
    }

    @Override
    public void run() {
        executorService.scheduleWithFixedDelay(this::exceptionHandler, 0, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void close() {
        executorService.shutdown();
    }
}
