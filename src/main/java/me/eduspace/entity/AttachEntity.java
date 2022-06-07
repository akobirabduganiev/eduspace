package me.eduspace.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "attach")
public class AttachEntity {
    @Id
    private String id;
    @Column
    private String path;
    @Column
    private String extension;
    @Column(name = "origin_name")
    private String originName;
    @Column()
    private Long size;
    @Column
    private LocalDateTime createdDate = LocalDateTime.now();

}
