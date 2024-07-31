package com.jdh.springSecurityJwt.config.exception.token;

import com.jdh.springSecurityJwt.api.token.exception.RefreshTokenException;
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
public class RefreshTokenApiExceptionAdvice {

    @ExceptionHandler({RefreshTokenException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest req, final RefreshTokenException e) {
        log.warn("[RefreshTokenApiExceptionAdvice] RefreshTokenException :: {}", e.getRefreshTokenExceptionResult().getMessage());

        return ResponseEntity
                .status(e.getRefreshTokenExceptionResult().getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(e.getRefreshTokenExceptionResult().getCode())
                        .errorMsg(e.getRefreshTokenExceptionResult().getMessage())
                        .build());
    }

}
