package com.jdh.springSecurityJwt.api.login.controller;

import com.jdh.springSecurityJwt.api.common.response.entity.ApiResponseEntity;
import com.jdh.springSecurityJwt.api.login.application.LoginService;
import com.jdh.springSecurityJwt.api.login.dto.request.LoginRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseEntity> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        // login 체크 후 token 생성
        var loginInfo = loginService.login(loginRequestDTO);

        return ApiResponseEntity.successResponseEntity(loginInfo);
    }

}
