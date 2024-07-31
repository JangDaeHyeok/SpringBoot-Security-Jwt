package com.jdh.springSecurityJwt.api.user.application;

import com.jdh.springSecurityJwt.api.user.dto.response.UserGetResponseDTO;

public interface UserGetService {

    /**
     * 사용자 idx를 이용해 사용자 정보 조회
     *
     * @param id 사용자 idx
     * @return 사용자 정보 UserGetResponseDTO
     */
    UserGetResponseDTO getUserById(final long id);

    /**
     * 사용자 id를 이용해 사용자 정보 조회
     *
     * @param userId 사용자 id
     * @return 사용자 정보 UserGetResponseDTO
     */
    UserGetResponseDTO getUserByUserId(final String userId);

}
