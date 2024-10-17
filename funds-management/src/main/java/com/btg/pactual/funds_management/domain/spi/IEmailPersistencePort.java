package com.btg.pactual.funds_management.domain.spi;

public interface IEmailPersistencePort {
    void sendEmail(String toEmail, String subject, String body);
}
