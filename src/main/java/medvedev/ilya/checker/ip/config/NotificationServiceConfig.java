package medvedev.ilya.checker.ip.config;

import lombok.Setter;
import medvedev.ilya.checker.ip.service.notification.NotificationService;
import medvedev.ilya.checker.ip.service.notification.email.EmailNotificationService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Configuration
@ConfigurationProperties(prefix = "notification.email")
@Setter
@Validated
public class NotificationServiceConfig {
    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    private String subject;

    @Bean
    public NotificationService notificationService(final MailSender mailSender) {
        return new EmailNotificationService(mailSender, from, to, subject);
    }
}
