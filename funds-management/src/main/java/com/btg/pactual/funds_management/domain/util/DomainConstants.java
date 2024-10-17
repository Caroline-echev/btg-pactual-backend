package com.btg.pactual.funds_management.domain.util;

public class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException(UTILITY_CLASS_EXCEPTION_MESSAGE);
    }
    public static final String UTILITY_CLASS_EXCEPTION_MESSAGE = "The utility class cannot be instantiated.";
    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "El usuario no existe";
    public static final String FUND_NOT_FOUND_EXCEPTION_MESSAGE = "El fondo no existe";
    public static final String INSUFFICIENT_BALANCE = "No tiene saldo disponible para vincularse al fondo ";
    public  static final String ALREADY_SUBSCRIBED_EXCEPTION_MESSAGE = "El usuario ya está suscrito al fondo: ";
    public static final String INSUFFICIENT_AMOUNT = "No cumple con el monto mínimo del fondo: ";
    public static final int VALIDATION_MINIMUM_AMOUNT_ZERO = 0;
}
