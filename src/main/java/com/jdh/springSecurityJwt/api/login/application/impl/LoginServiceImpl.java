package com.jdh.springSecurityJwt.api.login.application.impl;

import com.jdh.springSecurityJwt.api.login.application.LoginService;
import com.jdh.springSecurityJwt.api.login.dto.request.LoginRequestDTO;
import com.jdh.springSecurityJwt.api.login.dto.response.LoginResponseDTO;
import com.jdh.springSecurityJwt.api.login.exception.LoginException;
import com.jdh.springSecurityJwt.api.login.exception.LoginExceptionResult;
import com.jdh.springSecurityJwt.api.token.vo.RefreshToken;
import com.jdh.springSecurityJwt.api.user.application.UserGetService;
import com.jdh.springSecurityJwt.api.user.dto.response.UserGetResponseDTO;
import com.jdh.springSecurityJwt.config.security.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserGetService userGetService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public LoginResponseDTO login(final LoginRequestDTO loginRequestDTO) {
        // 사용자 정보 조회
        UserGetResponseDTO userInfo = userGetService.getUserByUserId(loginRequestDTO.getUserId());

        // password 일치 여부 체크
        if(!bCryptPasswordEncoder.matches(loginRequestDTO.getPassword(), userInfo.password()))
            throw new LoginException(LoginExceptionResult.NOT_CORRECT);

        // jwt 토큰 생성
        String accessToken = jwtProvider.generateAccessToken(userInfo.id());

        // 기존에 가지고 있는 사용자의 refresh token 제거
        RefreshToken.removeUserRefreshToken(userInfo.id());

        // refresh token 생성 후 저장
        String refreshToken = jwtProvider.generateRefreshToken(userInfo.id());
        RefreshToken.putRefreshToken(refreshToken, userInfo.id());

        return LoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
