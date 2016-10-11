package medvedev.ilya.checker.ip.config;

import medvedev.ilya.checker.ip.service.checker.CheckerService;
import medvedev.ilya.checker.ip.service.checker.impl.IpCheckerService;
import medvedev.ilya.checker.ip.service.notification.NotificationService;
import medvedev.ilya.checker.ip.service.ip.IpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckerServiceConfig {
    @Bean
    public CheckerService checkerService(
            final IpService ipService,
            final NotificationService notificationService,
            @Value("${ip.checker.timeout}") final int timeout
    ) {
        return new IpCheckerService(ipService, notificationService, timeout);
    }
}
