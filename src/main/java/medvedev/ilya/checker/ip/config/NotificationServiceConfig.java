package medvedev.ilya.checker.ip.config;

import medvedev.ilya.checker.ip.service.notification.NotificationService;
import medvedev.ilya.checker.ip.service.notification.email.EmailNotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

@Configuration
public class NotificationServiceConfig {
    @Bean
    public NotificationService notificationService(
            final MailSender mailSender,
            @Value("${notification.email.from}") final String from,
            @Value("${notification.email.to}") final String to,
            @Value("${notification.email.subject}") final String subject
    ) {
        return new EmailNotificationService(mailSender, from, to, subject);
    }
}
