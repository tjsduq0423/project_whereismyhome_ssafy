package com.whereismyhome.exception.response;

import com.whereismyhome.exception.ExceptionCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponse {
    private int status;
    private String msg;

    public static ResponseEntity<ErrorResponse> setErrorResponse(ExceptionCode exceptionCode) {
        return ResponseEntity.status(exceptionCode.getStatus())
                .body(ErrorResponse.builder()
                        .status(exceptionCode.getStatus())
                        .msg(exceptionCode.getMessage())
                        .build());
    }
}
