package me.eduspace.entity;

import lombok.Getter;
import lombok.Setter;
import me.eduspace.enums.Permission;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_group")
public class UserGroupEntity extends BaseEntity {
//    ->id, user_id, group_id, PERMISSION
    @Column
    private Permission permission;

    @Column(name = "group_id", nullable = false)
    private Long groupId;
    @ManyToOne
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private GroupEntity group;

    @Column(name = "user_id", nullable = false)
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;
}