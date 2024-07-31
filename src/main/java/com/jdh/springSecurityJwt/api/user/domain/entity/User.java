package com.jdh.springSecurityJwt.api.user.domain.entity;

import com.jdh.springSecurityJwt.api.common.entity.RegModDt;
import com.jdh.springSecurityJwt.api.user.domain.entity.value.LoginInfo;
import com.jdh.springSecurityJwt.api.user.domain.entity.value.RoleInfo;
import com.jdh.springSecurityJwt.api.user.domain.entity.value.UserInfo;
import com.jdh.springSecurityJwt.api.user.dto.request.UserAddRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE user SET del_yn = true WHERE id = ?")
@SQLRestriction("del_yn = false")
public class User extends RegModDt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private LoginInfo loginInfo;

    @Embedded
    private RoleInfo roleInfo;

    @Embedded
    private UserInfo userInfo;

    private boolean delYn = Boolean.FALSE; // 삭제 여부 기본값 false

    /**
     * UserAddRequestDTO to User Entity
     *
     * @param dto UserAddRequestDTO
     * @return User Entity
     */
    public static User of(UserAddRequestDTO dto) {
        // Login Info
        LoginInfo inputLoginInfo = LoginInfo.builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .build();

        // User Info
        UserInfo inputUserInfo = UserInfo.builder()
                .name(dto.getName())
                .tel(dto.getTel())
                .build();

        // Role Info
        RoleInfo inputRoleInfo = RoleInfo.builder()
                .roleName(dto.getRoleName())
                .build();

        return User.builder()
                .loginInfo(inputLoginInfo)
                .userInfo(inputUserInfo)
                .roleInfo(inputRoleInfo)
                .build();
    }

}
