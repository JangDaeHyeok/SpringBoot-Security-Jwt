package com.jdh.springSecurityJwt.api.user.domain.entity.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Embeddable
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo {

    @Column(nullable = false)
    private String name;

    private String tel;

}
