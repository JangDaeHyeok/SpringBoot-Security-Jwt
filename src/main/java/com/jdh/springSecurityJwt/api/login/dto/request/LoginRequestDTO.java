package com.jdh.springSecurityJwt.api.login.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginRequestDTO {

    @NotNull
    @NotEmpty
    private String userId;

    @NotNull
    @NotEmpty
    private String password;

}
