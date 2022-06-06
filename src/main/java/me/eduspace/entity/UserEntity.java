package me.eduspace.entity;

import lombok.Getter;
import lombok.Setter;
import me.eduspace.enums.Gender;
import me.eduspace.enums.GeneralStatus;
import me.eduspace.enums.UserRole;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table
public class UserEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String phone;

    @Column
    private LocalDate birthDate;

    @Column
    @Enumerated(EnumType.STRING)
    private GeneralStatus status;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "attach_id")
    private String attachId;
    @OneToOne
    @JoinColumn(name = "attach_id", insertable = false, unique = true)
    private AttachEntity attach;


}
