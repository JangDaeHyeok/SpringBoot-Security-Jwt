package com.jdh.springSecurityJwt.api.user.dto.request;

import com.jdh.springSecurityJwt.api.user.enums.RoleName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserAddRequestDTO {

    @NotNull
    @NotEmpty
    private String userId;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String name;

    private String tel;

    private RoleName roleName;

}
