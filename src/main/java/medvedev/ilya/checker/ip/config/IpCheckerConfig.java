package medvedev.ilya.checker.ip.config;

import medvedev.ilya.checker.ip.checker.IpChecker;
import medvedev.ilya.checker.ip.service.EmailNotificationService;
import medvedev.ilya.checker.ip.service.IpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

import java.net.URL;

@Configuration
public class IpCheckerConfig {
    @Bean
    public IpService ipService(@Value("${ip.service.url}") final URL url) {
        return new IpService(url);
    }

    @Bean
    public EmailNotificationService emailNotificationService(
            final MailSender mailSender,
            @Value("${notification.email.from}") final String from,
            @Value("${notification.email.to}") final String to,
            @Value("${notification.email.subject}") final String subject
    ) {
        return new EmailNotificationService(mailSender, from, to, subject);
    }

    @Bean
    public IpChecker ipChecker(
            final IpService ipService,
            final EmailNotificationService notificationService,
            @Value("${ip.checker.timeout}") final int timeout
    ) {
        return new IpChecker(ipService, notificationService, timeout);
    }
}
