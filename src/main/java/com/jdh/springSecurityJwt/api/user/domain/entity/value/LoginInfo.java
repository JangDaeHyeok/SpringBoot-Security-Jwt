package com.jdh.springSecurityJwt.api.user.domain.entity.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Embeddable
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginInfo {

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    /**
     * password 암호화
     *
     * @param passwordEncoder PasswordEncoder
     */
    public void encryptPassword(PasswordEncoder passwordEncoder) {
        // 비밀번호가 빈 값인 경우 exception
        if(password == null || password.isEmpty()) {
            throw new IllegalArgumentException("empty password!");
        }

        // 암호화
        this.password = passwordEncoder.encode(this.password);
    }

}
