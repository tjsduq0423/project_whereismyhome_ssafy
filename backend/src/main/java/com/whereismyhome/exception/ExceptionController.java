package com.whereismyhome.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity businessLogicException(BusinessLogicException e) {
        ExceptionCode exceptionCode = e.getExceptionCode();

        return ResponseEntity.status(exceptionCode.getStatus()).body(e.getMessage());
    }
}
