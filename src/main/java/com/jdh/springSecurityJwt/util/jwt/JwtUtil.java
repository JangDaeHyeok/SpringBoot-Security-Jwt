package com.jdh.springSecurityJwt.util.jwt;

import lombok.experimental.UtilityClass;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@UtilityClass
public class JwtUtil {

    /**
     * Spring Security Context에서 로그인한 사용자의 id 조회
     *
     * @param authentication Authentication
     * @return 로그인한 사용자의 id
     * @throws AccessDeniedException AccessDeniedException
     */
    public long getLoginId(final Authentication authentication) throws AccessDeniedException {
        // 정상적으로 로그인한 사용자 정보인지 체크
        checkAuth(authentication);

        return Long.parseLong(authentication.getPrincipal().toString());
    }

    /**
     * 정상적으로 로그인한 사용자 정보인지 체크
     *
     * @param authentication Authentication
     * @throws AccessDeniedException AccessDeniedException
     */
    private void checkAuth(final Authentication authentication) throws AccessDeniedException {
        if(authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("로그인 정보가 존재하지 않습니다.");
        }
    }

}
