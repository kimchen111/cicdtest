package com.ianunei.noname.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 帅小鸦
 * @date 2022/5/12
 */

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    /**
     * `@GeneratedValue 注解还需要在实体类上添加 @GenericGenerator(name = "jpa-uuid", strategy = "uuid") 才能正常使用
     */
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    protected String uuid;

    /**
     * `@CreatedDate 和 @LastModifiedDate 注解
     * 需要在实体上加上 @EntityListeners(AuditingEntityListener.class)
     * 以及在启动类上加上 @EnableJpaAuditing 才能生效
     */
    @CreatedDate
    @Column(columnDefinition = "DATETIME(0) NOT NULL DEFAULT CURRENT_TIMESTAMP")
    protected LocalDateTime createdTime;

    @LastModifiedDate
    @Column(columnDefinition = "DATETIME(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP")
    protected LocalDateTime updatedTime;
}
