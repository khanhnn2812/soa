package com.hitex.yousim.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Configuration
@EnableJpaAuditing
@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseModel implements Serializable {
    @Column(name = "create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Column(name = "create_user")
    private String createUser;
    @Column(name = "update_user")
    private String updateUser;
}
