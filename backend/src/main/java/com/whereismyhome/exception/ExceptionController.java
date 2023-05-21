package com.whereismyhome.exception;

import com.whereismyhome.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity businessLogicException(BusinessLogicException e) {

        return ErrorResponse.setErrorResponse(e.getExceptionCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity globalException(Exception e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

}
