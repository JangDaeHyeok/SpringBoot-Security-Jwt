package com.jdh.springSecurityJwt.api.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserExceptionResult {

    NOT_EXISTS(HttpStatus.BAD_REQUEST, "U0001", "존재하지 않는 사용자입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
