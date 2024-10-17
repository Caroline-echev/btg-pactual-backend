package com.btg.pactual.funds_management.domain.spi;

public interface ISmsPersistencePort {
    String sendMessageSms(String phoneNumber, String message);
}
