package com.chanthai.phoneshop.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditEntity {
    @CreatedDate
    private LocalDateTime dateCreate;
    @LastModifiedDate
    private LocalDateTime dateUpdate;
    @CreatedBy
    private String userCreate;
    @LastModifiedBy
    private String userUpdate;
}
