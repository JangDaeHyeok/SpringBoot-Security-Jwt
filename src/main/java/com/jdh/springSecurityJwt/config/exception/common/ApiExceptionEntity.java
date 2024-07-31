package com.jdh.springSecurityJwt.config.exception.common;

import lombok.Builder;

@Builder
public record ApiExceptionEntity(String errorCode, String errorMsg) {
}