package com.whereismyhome.exception;

import lombok.Getter;


public enum ExceptionCode {
    DUPLICATE_ID(409,"중복된 아이디가 존재합니다."),
    BAD_PARAM(404,"아이디 혹은 비밀번호가 틀렸습니다.");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
