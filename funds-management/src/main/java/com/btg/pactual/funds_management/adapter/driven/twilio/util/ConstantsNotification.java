package com.btg.pactual.funds_management.adapter.driven.twilio.util;

public class ConstantsNotification {
    private ConstantsNotification() {
        throw new IllegalStateException(UTILITY_CLASS_EXCEPTION_MESSAGE);
    }
    public static final String UTILITY_CLASS_EXCEPTION_MESSAGE = "The utility class cannot be instantiated.";

    public static final String METHOD_SMS = "sms";
    public static final String METHOD_EMAIL = "email";
    public static final String MESSAGE_SEND = "Message was sent successfully";
}
