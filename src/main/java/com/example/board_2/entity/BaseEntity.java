package com.example.board_2.entity;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
abstract class BaseEntity {

    //   등록시간
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    //    최종수정시간
    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
