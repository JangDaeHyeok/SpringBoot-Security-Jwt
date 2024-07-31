package com.jdh.springSecurityJwt.config.exception.common;

import com.jdh.springSecurityJwt.config.exception.common.enums.ApiExceptionEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class ApiExceptionAdvice {

    // access denied exception
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest req, final AccessDeniedException e) {
        log.error("[ApiExceptionAdvice] access denied exception :: ", e);

        return ResponseEntity
                .status(ApiExceptionEnum.ACCESS_DENIED.getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(ApiExceptionEnum.ACCESS_DENIED.getCode())
                        .errorMsg(ApiExceptionEnum.ACCESS_DENIED.getMessage())
                        .build());
    }

    // bad request exception
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        log.error("[ApiExceptionAdvice] bad request exception :: ", e);

        return ResponseEntity
                .status(ApiExceptionEnum.BAD_REQUEST.getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(ApiExceptionEnum.BAD_REQUEST.getCode())
                        .errorMsg(ApiExceptionEnum.BAD_REQUEST.getMessage())
                        .build());
    }

    // method not allowed exception
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        log.error("[ApiExceptionAdvice] method not allowed exception :: ", e);

        return ResponseEntity
                .status(ApiExceptionEnum.METHOD_NOT_ALLOWED.getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(ApiExceptionEnum.METHOD_NOT_ALLOWED.getCode())
                        .errorMsg(ApiExceptionEnum.METHOD_NOT_ALLOWED.getMessage())
                        .build());
    }

    // not found exception
    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest req, NoHandlerFoundException e) {
        log.error("[ApiExceptionAdvice] not found exception :: ", e);

        return ResponseEntity
                .status(ApiExceptionEnum.NOT_FOUND.getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(ApiExceptionEnum.NOT_FOUND.getCode())
                        .errorMsg(ApiExceptionEnum.NOT_FOUND.getMessage())
                        .build());
    }

    // runtime(unchecked) exception
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest req, final RuntimeException e) {
        log.error("[ApiExceptionAdvice] runtime exception :: ", e);

        return ResponseEntity
                .status(ApiExceptionEnum.RUNTIME_EXCEPTION.getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(ApiExceptionEnum.RUNTIME_EXCEPTION.getCode())
                        .errorMsg(e.getMessage())
                        .build());
    }

    // exception
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest req, final Exception e) {
        log.error("[ApiExceptionAdvice] exception :: ", e);

        return ResponseEntity
                .status(ApiExceptionEnum.INTERNAL_SERVER_ERROR.getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(ApiExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
                        .errorMsg(e.getMessage())
                        .build());
    }
}