package me.eduspace.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.eduspace.enums.Gender;
import me.eduspace.enums.UserStatus;
import me.eduspace.enums.UserRole;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity extends BaseEntity {
//id , name, surname, phone (uniqe), ROLE, USER_STATUS, birthDate, GENDER, attach_id, password
    @Column
    private String name;

    @Column
    private String surname;

    @Column(unique = true)
    private String phone;

    @Column
    private String password;

    @Column
    private LocalDate birthDate;

    @Column
    private String imageLink;

    @Column
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private Boolean locked = false;

    @Column
    private Boolean enabled = false;


    public UserEntity(String name, String surname, String phone, String password, UserRole role, LocalDate birthDate, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
