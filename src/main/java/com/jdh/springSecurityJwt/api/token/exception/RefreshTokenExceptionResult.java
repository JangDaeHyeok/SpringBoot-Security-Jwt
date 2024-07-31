package com.jdh.springSecurityJwt.api.token.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RefreshTokenExceptionResult {

    NOT_EXIST(HttpStatus.UNAUTHORIZED, "t0001", "Refresh Token이 존재하지 않습니다."),
    INVALID(HttpStatus.UNAUTHORIZED, "t0002", "Refresh Token이 만료되었거나 정상적인 Token이 아닙니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
