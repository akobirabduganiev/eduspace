package me.eduspace.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {
    @Id
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifedDate;
}
