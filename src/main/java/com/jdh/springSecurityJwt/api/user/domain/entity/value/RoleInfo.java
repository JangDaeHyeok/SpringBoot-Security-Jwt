package com.jdh.springSecurityJwt.api.user.domain.entity.value;

import com.jdh.springSecurityJwt.api.user.enums.RoleName;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Embeddable
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleInfo {

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private RoleName roleName;

}
