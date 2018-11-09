package medvedev.ilya.checker.ip.service.notification.email;

import lombok.RequiredArgsConstructor;
import medvedev.ilya.checker.ip.service.notification.NotificationServiceException;
import medvedev.ilya.checker.ip.service.notification.NotificationService;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@RequiredArgsConstructor
public class EmailNotificationService implements NotificationService {
    private final MailSender mailSender;
    private final String from;
    private final String to;
    private final String subject;

    @Override
    public void sendNotification(final String notification) {
        final SimpleMailMessage msg = new SimpleMailMessage();

        msg.setFrom(from);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(notification);

        try {
            mailSender.send(msg);
        } catch (final MailException e) {
            throw new NotificationServiceException(e);
        }
    }
}
