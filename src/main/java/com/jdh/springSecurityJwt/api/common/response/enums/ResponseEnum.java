package com.jdh.springSecurityJwt.api.common.response.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseEnum {
    OK(HttpStatus.OK, "success", "성공"),
    FAIL(HttpStatus.OK, "fail", "실패");

    private final HttpStatus status;
    private final String result;
    private final String message;

    ResponseEnum(HttpStatus status, String result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }
}
