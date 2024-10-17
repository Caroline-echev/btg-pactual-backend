package com.btg.pactual.funds_management.adapter.driven.twilio.adapter;
import com.btg.pactual.funds_management.domain.spi.ISmsPersistencePort;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.btg.pactual.funds_management.adapter.driven.twilio.util.ConstantsNotification.MESSAGE_SEND;

@Service
public class SmsPersistenceAdapter implements ISmsPersistencePort {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.from-number}")
    private String fromNumber;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    @Override
    public String sendMessageSms(String phoneNumber, String message) {
         Message.creator(
                new com.twilio.type.PhoneNumber(phoneNumber),
                new com.twilio.type.PhoneNumber(fromNumber),
                message).create();

        return MESSAGE_SEND;
    }
}