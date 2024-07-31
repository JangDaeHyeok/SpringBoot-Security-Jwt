package com.jdh.springSecurityJwt.api.login.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Login Exception
 */
@Getter
@RequiredArgsConstructor
public class LoginException extends RuntimeException {

    private final LoginExceptionResult loginErrorResult;

}
