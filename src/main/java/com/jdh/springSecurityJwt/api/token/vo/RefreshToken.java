package com.jdh.springSecurityJwt.api.token.vo;

import com.jdh.springSecurityJwt.api.token.exception.RefreshTokenException;
import com.jdh.springSecurityJwt.api.token.exception.RefreshTokenExceptionResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * RefreshToken 저장 객체
 *
 * <p>
 * 해당 프로젝트는 스프링 시큐리티 위주의 프로젝트이기 때문에 간단하게 구현
 * 운영환경에서는 해당 방식이 아닌 Redis 사용을 추천
 * Redis 에서 만료시간을 설정하여 관리
 * </p>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshToken {

    protected static final Map<String, Long> refreshTokens = new HashMap<>();

    /**
     * refresh token get
     *
     * @param refreshToken refresh token
     * @return id
     */
    public static Long getRefreshToken(final String refreshToken) {
        return Optional.ofNullable(refreshTokens.get(refreshToken))
                .orElseThrow(() -> new RefreshTokenException(RefreshTokenExceptionResult.NOT_EXIST));
    }

    /**
     * refresh token put
     *
     * @param refreshToken refresh token
     * @param id id
     */
    public static void putRefreshToken(final String refreshToken, Long id) {
        refreshTokens.put(refreshToken, id);
    }

    /**
     * refresh token remove
     *
     * @param refreshToken refresh token
     */
    private static void removeRefreshToken(final String refreshToken) {
        refreshTokens.remove(refreshToken);
    }

    // user refresh token remove
    public static void removeUserRefreshToken(final long refreshToken) {
        for(Map.Entry<String, Long> entry : refreshTokens.entrySet()) {
            if(entry.getValue() == refreshToken) {
                removeRefreshToken(entry.getKey());
            }
        }
    }

}
