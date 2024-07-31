package com.jdh.springSecurityJwt.api.login.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LoginExceptionResult {

    NOT_CORRECT(HttpStatus.OK, "l0001", "아이디 혹은 비밀번호가 일치하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
