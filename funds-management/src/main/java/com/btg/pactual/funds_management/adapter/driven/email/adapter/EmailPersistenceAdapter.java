package com.btg.pactual.funds_management.adapter.driven.email.adapter;

import com.btg.pactual.funds_management.domain.spi.IEmailPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RequiredArgsConstructor
public class EmailPersistenceAdapter implements IEmailPersistencePort {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String myEmail;
    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom(myEmail);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
