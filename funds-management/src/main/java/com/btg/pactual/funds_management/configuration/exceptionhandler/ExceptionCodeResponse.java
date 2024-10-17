package com.btg.pactual.funds_management.configuration.exceptionhandler;

import java.time.LocalDateTime;

public class ExceptionCodeResponse {
    private final String message;
    private final Integer code;
    private final String status;
    private final LocalDateTime timestamp;


    public ExceptionCodeResponse(String message, String status, LocalDateTime timestamp, Integer code) {

        this.message = message;

        this.status = status;

        this.timestamp = timestamp;

        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public Integer getCode() {
        return code;
    }

}
