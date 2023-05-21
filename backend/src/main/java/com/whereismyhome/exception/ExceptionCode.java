package com.whereismyhome.exception;

import lombok.Getter;


public enum ExceptionCode {
    DUPLICATE_ID(409,"중복된 아이디가 존재합니다."),
    BAD_PARAM(404,"아이디 혹은 비밀번호가 틀렸습니다."),
    USER_NOT_FOUND(404,"해당 회원을 찾을 수 없습니다."),
    BOARD_NOT_FOUND(404,"해당 게시글을 찾을 수 없습니다."),
    HOUSE_NOT_FOUND(404, "해당 아파트를 찾을 수 없습니다.");
    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
