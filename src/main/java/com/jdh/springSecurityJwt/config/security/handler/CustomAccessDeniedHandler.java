package com.jdh.springSecurityJwt.config.security.handler;

import com.google.gson.JsonObject;
import com.jdh.springSecurityJwt.config.exception.common.enums.ApiExceptionEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Custom Access Denied Handler Handler
 */
@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("[CustomAccessDeniedHandler] :: {}", accessDeniedException.getMessage());
        log.info("[CustomAccessDeniedHandler] :: {}", request.getRequestURL());
        log.info("[CustomAccessDeniedHandler] :: 토근 정보가 만료되었거나 존재하지 않음");

        response.setStatus(ApiExceptionEnum.FORBIDDEN.getStatus().value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        JsonObject returnJson = new JsonObject();
        returnJson.addProperty("errorCode", ApiExceptionEnum.FORBIDDEN.getCode());
        returnJson.addProperty("errorMsg", ApiExceptionEnum.FORBIDDEN.getMessage());

        PrintWriter out = response.getWriter();
        out.print(returnJson);
    }
}
