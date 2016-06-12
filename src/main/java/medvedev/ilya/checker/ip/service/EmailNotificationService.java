package medvedev.ilya.checker.ip.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailNotificationService {
    private final MailSender mailSender;
    private final String from;
    private final String to;
    private final String subject;

    public EmailNotificationService(
            final MailSender mailSender,
            final String from,
            final String to,
            final String subject
    ) {
        this.mailSender = mailSender;
        this.from = from;
        this.to = to;
        this.subject = subject;
    }

    public void sendNotification(final String notification) {
        final SimpleMailMessage msg = new SimpleMailMessage();

        msg.setFrom(from);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(notification);

        mailSender.send(msg);
    }
}
