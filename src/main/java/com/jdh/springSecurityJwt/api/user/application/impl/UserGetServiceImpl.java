package com.jdh.springSecurityJwt.api.user.application.impl;

import com.jdh.springSecurityJwt.api.user.application.UserGetService;
import com.jdh.springSecurityJwt.api.user.domain.repository.UserRepository;
import com.jdh.springSecurityJwt.api.user.dto.response.UserGetResponseDTO;
import com.jdh.springSecurityJwt.api.user.exception.UserException;
import com.jdh.springSecurityJwt.api.user.exception.UserExceptionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserGetServiceImpl implements UserGetService {

    private final UserRepository userRepository;

    /**
     * 사용자 idx를 이용해 사용자 정보 조회
     *
     * @param id 사용자 idx
     * @return 사용자 정보 UserGetResponseDTO
     */
    @Override
    public UserGetResponseDTO getUserById(final long id) {
        return userRepository.findById(id)
                .map(UserGetResponseDTO::of)
                .orElseThrow(() -> new UserException(UserExceptionResult.NOT_EXISTS));
    }

    /**
     * 사용자 id를 이용해 사용자 정보 조회
     *
     * @param userId 사용자 id
     * @return 사용자 정보 UserGetResponseDTO
     */
    @Override
    public UserGetResponseDTO getUserByUserId(final String userId) {
        return userRepository.findByLoginInfoUserId(userId)
                .map(UserGetResponseDTO::of)
                .orElseThrow(() -> new UserException(UserExceptionResult.NOT_EXISTS));
    }
}
