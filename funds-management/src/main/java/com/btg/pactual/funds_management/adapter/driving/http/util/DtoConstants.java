package com.btg.pactual.funds_management.adapter.driving.http.util;

public class DtoConstants {

    private DtoConstants() {
        throw new IllegalStateException(UTILITY_CLASS_EXCEPTION_MESSAGE);
    }

    public static final String UTILITY_CLASS_EXCEPTION_MESSAGE = "The utility class cannot be instantiated.";
    public static final String USER_ID_BLANK_MESSAGE = "The user id cannot be blank";
    public static final String FUND_ID_BLANK_MESSAGE = "The fund id cannot be blank";
    public static final String AMOUNT_NOT_NULL_MESSAGE = "The amount cannot be null";
    public static final String AMOUNT_POSITIVE_MESSAGE = "The amount must be positive";
    public static final String SMS_OR_EMAIL_NOTIFICATION = "SMS or Email notification preference must be specified";
    public static final String AMOUNT_MIN_VALUE = "0.01";
}
