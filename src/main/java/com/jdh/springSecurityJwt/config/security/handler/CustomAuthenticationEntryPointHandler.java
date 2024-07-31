package com.jdh.springSecurityJwt.config.security.handler;

import com.google.gson.JsonObject;
import com.jdh.springSecurityJwt.config.exception.common.enums.ApiExceptionEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Custom Authentication Entry Point Handler
 */
@Slf4j
@Component
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.info("[CustomAuthenticationEntryPointHandler] :: {}", authException.getMessage());
        log.info("[CustomAuthenticationEntryPointHandler] :: {}", request.getRequestURL());
        log.info("[CustomAuthenticationEntryPointHandler] :: 토근 정보가 만료되었거나 존재하지 않음");

        response.setStatus(ApiExceptionEnum.ACCESS_DENIED.getStatus().value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        JsonObject returnJson = new JsonObject();
        returnJson.addProperty("errorCode", ApiExceptionEnum.ACCESS_DENIED.getCode());
        returnJson.addProperty("errorMsg", ApiExceptionEnum.ACCESS_DENIED.getMessage());

        PrintWriter out = response.getWriter();
        out.print(returnJson);
    }

}
