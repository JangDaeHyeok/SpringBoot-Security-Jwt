package com.jdh.springSecurityJwt.config.exception.user;

import com.jdh.springSecurityJwt.api.user.exception.UserException;
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
public class UserApiExceptionAdvice {

    @ExceptionHandler({UserException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest req, final UserException e) {
        log.warn("[UserApiExceptionAdvice] UserException :: {}", e.getUserExceptionResult().getMessage());

        return ResponseEntity
                .status(e.getUserExceptionResult().getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(e.getUserExceptionResult().getCode())
                        .errorMsg(e.getUserExceptionResult().getMessage())
                        .build());
    }

}
