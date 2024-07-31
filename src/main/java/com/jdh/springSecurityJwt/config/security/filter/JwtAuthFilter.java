package com.jdh.springSecurityJwt.config.security.filter;

import com.jdh.springSecurityJwt.api.user.application.UserGetService;
import com.jdh.springSecurityJwt.config.security.provider.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    private final UserGetService userGetService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader("Authorization");

        String username = null;

        // Bearer token 검증 후 user name 조회
        if(token != null && !token.isEmpty()) {
            String jwtToken = token.substring(7);

            username = jwtProvider.getUsernameFromToken(jwtToken);
        }

        // token 검증 완료 후 SecurityContextHolder 내 인증 정보가 없는 경우 저장
        if(username != null && !username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Spring Security Context Holder 인증 정보 set
            SecurityContextHolder.getContext().setAuthentication(getUserAuth(username));
        }

        filterChain.doFilter(request,response);
    }

    /**
     * token의 사용자 idx를 이용하여 사용자 정보 조회하고, UsernamePasswordAuthenticationToken 생성
     * @param username 사용자 idx
     * @return 사용자 UsernamePasswordAuthenticationToken
     */
    private UsernamePasswordAuthenticationToken getUserAuth(String username) {
        var userInfo = userGetService.getUserById(Long.parseLong(username));

        return new UsernamePasswordAuthenticationToken(userInfo.id(),
                userInfo.password(),
                Collections.singleton(new SimpleGrantedAuthority(userInfo.roleName().name()))
        );
    }

}
