package medvedev.ilya.checker.ip.config;

import lombok.Setter;
import medvedev.ilya.checker.ip.service.checker.CheckerService;
import medvedev.ilya.checker.ip.service.checker.impl.IpCheckerService;
import medvedev.ilya.checker.ip.service.ip.IpService;
import medvedev.ilya.checker.ip.service.notification.NotificationService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Configuration
@ConfigurationProperties(prefix = "ip.checker")
@Setter
@Validated
public class CheckerServiceConfig {
    @NotNull
    private Integer timeout;

    @Bean
    public CheckerService checkerService(final IpService ipService, final NotificationService notificationService) {
        return new IpCheckerService(ipService, notificationService, timeout);
    }
}
