package com.jdh.springSecurityJwt.api.user.application;

public interface UserDelService {

    /**
     * 사용자 삭제
     *
     * @param id 사용자 idx
     */
    void delUser(final long id);

}
