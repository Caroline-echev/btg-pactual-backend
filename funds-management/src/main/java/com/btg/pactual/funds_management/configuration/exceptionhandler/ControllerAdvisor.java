package com.btg.pactual.funds_management.configuration.exceptionhandler;


import com.btg.pactual.funds_management.domain.exception.FundNotFoundException;
import com.btg.pactual.funds_management.domain.exception.InsufficientBalanceException;
import com.btg.pactual.funds_management.domain.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionCodeResponse> handlerArgumentInvalidException(MethodArgumentNotValidException exception) {

        FieldError firstFieldError = exception.getFieldErrors().get(0);
        return ResponseEntity.badRequest().body(new ExceptionCodeResponse(firstFieldError.getDefaultMessage(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ExceptionCodeResponse> handlerInsufficientBalanceException(InsufficientBalanceException exception) {
        return ResponseEntity.badRequest().body(new ExceptionCodeResponse(exception.getMessage(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionCodeResponse> handlerUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity.badRequest().body(new ExceptionCodeResponse(exception.getMessage(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(FundNotFoundException.class)
    public ResponseEntity<ExceptionCodeResponse> handlerFundNotFoundException(FundNotFoundException exception) {
        return ResponseEntity.badRequest().body(new ExceptionCodeResponse(exception.getMessage(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));
    }
}
