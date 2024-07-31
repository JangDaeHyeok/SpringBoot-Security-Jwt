package com.jdh.springSecurityJwt.config.exception.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiExceptionEnum {

    RUNTIME_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "E0001")
    , ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "E0002", "인증되지 않은 사용자입니다.")
    , BAD_REQUEST(HttpStatus.BAD_REQUEST, "E0003", "필수 요청 변수가 누락되었습니다.")
    , METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "E0004", "요청 메소드를 확인해주세요.")
    , NOT_FOUND(HttpStatus.NOT_FOUND, "E0004", "존재하지 않는 API입니다.")
    , INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E9999", "기타 오류")

    , FORBIDDEN(HttpStatus.FORBIDDEN, "S0001", "권한이 없습니다.");


    private final HttpStatus status;
    private final String code;
    private String message;

    ApiExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ApiExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}