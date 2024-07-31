package com.jdh.springSecurityJwt.api.user.application.impl;

import com.jdh.springSecurityJwt.api.user.application.UserDelService;
import com.jdh.springSecurityJwt.api.user.domain.entity.User;
import com.jdh.springSecurityJwt.api.user.domain.repository.UserRepository;
import com.jdh.springSecurityJwt.api.user.exception.UserException;
import com.jdh.springSecurityJwt.api.user.exception.UserExceptionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserDelServiceImpl implements UserDelService {

    private final UserRepository userRepository;

    /**
     * 사용자 삭제
     *
     * @param id 사용자 idx
     */
    @Override
    @Transactional
    public void delUser(final long id) {
        // 사용자 정보 조회
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(UserExceptionResult.NOT_EXISTS));

        // delete
        userRepository.delete(user);
    }
}
