package me.eduspace.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.eduspace.enums.Gender;
import me.eduspace.enums.UserRole;
import me.eduspace.enums.UserStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private LocalDate birthDate;

    @Column
    private String imageLink;

    @Column
    private String phone;

    @Column
    @Enumerated(EnumType.STRING)
    @Type(type = "list-array")
    private List<UserRole> roleList;

    @Column
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private Boolean locked = false;

    @Column
    private Boolean enabled = false;


    public UserEntity(String name, String surname, String email, String password, UserRole role, LocalDate birthDate, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.roleList.add(role);
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Optional<String> getImageLink() {
        return Optional.ofNullable(imageLink);
    }

    public void setRole(UserRole role){
        this.roleList.add(role);
    }
}
