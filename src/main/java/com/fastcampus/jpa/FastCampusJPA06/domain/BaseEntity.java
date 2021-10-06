package com.fastcampus.jpa.FastCampusJPA06.domain;

import com.fastcampus.jpa.FastCampusJPA06.domain.listener.Auditable;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity implements Auditable {
    @CreatedDate
    @Column(nullable = false,updatable = false,columnDefinition = "datetime(6) default now(6) comment '생성시간'")
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(nullable = false,columnDefinition = "datetime(6) default now(6) comment '수정시간'")
    private LocalDateTime updateAt;

}
