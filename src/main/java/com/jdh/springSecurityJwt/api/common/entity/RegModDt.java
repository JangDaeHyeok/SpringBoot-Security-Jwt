package com.jdh.springSecurityJwt.api.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class RegModDt {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime regDt;

    @LastModifiedDate
    private LocalDateTime modDt;

}
