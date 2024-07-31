package com.jdh.springSecurityJwt.api.user.application;

import com.jdh.springSecurityJwt.api.user.dto.request.UserAddRequestDTO;

public interface UserAddService {

    /**
     * 사용자 추가
     *
     * @param userAddRequestDTO UserAddRequestDTO
     */
    void addUser(final UserAddRequestDTO userAddRequestDTO);

}
