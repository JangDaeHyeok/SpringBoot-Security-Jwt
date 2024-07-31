package com.jdh.springSecurityJwt.api.user.controller;

import com.jdh.springSecurityJwt.api.common.response.entity.ApiResponseEntity;
import com.jdh.springSecurityJwt.api.user.application.UserDelService;
import com.jdh.springSecurityJwt.api.user.application.UserGetService;
import com.jdh.springSecurityJwt.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserGetService userGetService;

    private final UserDelService userDelService;

    @GetMapping("/user")
    public ResponseEntity<ApiResponseEntity> dashboard(Authentication authentication) {
        // 사용자 정보 조회
        var result = userGetService.getUserById(JwtUtil.getLoginId(authentication));

        return ApiResponseEntity.successResponseEntity(result);
    }

    @DeleteMapping("/user")
    public ResponseEntity<ApiResponseEntity> delete(Authentication authentication) {
        // 사용자 정보 삭제
        userDelService.delUser(JwtUtil.getLoginId(authentication));

        return ApiResponseEntity.successResponseEntity();
    }

}
