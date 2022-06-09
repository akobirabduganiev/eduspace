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
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String phone;

    @Column
    private String password;

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

    public UserEntity() {

    }
}
