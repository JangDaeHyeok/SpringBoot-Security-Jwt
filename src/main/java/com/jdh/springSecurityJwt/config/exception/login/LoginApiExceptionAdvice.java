package com.jdh.springSecurityJwt.config.exception.login;

import com.jdh.springSecurityJwt.api.login.exception.LoginException;
import com.jdh.springSecurityJwt.config.exception.common.ApiExceptionEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class LoginApiExceptionAdvice {

    @ExceptionHandler({LoginException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest req, final LoginException e) {
        log.warn("[LoginApiExceptionAdvice] LoginException :: {}", e.getLoginErrorResult().getMessage());

        return ResponseEntity
                .status(e.getLoginErrorResult().getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(e.getLoginErrorResult().getCode())
                        .errorMsg(e.getLoginErrorResult().getMessage())
                        .build());
    }

}
