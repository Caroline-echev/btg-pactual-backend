package com.btg.pactual.funds_management.domain.exception;

public class FundNotFoundException extends RuntimeException{

    public FundNotFoundException(String message) {
        super(message);
    }
}
