package com.jdh.springSecurityJwt.api.login.application;

import com.jdh.springSecurityJwt.api.login.dto.request.LoginRequestDTO;
import com.jdh.springSecurityJwt.api.login.dto.response.LoginResponseDTO;

public interface LoginService {

    LoginResponseDTO login(final LoginRequestDTO loginRequestDTO);

}
